package com.ttgis.education.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Courseresource;
import com.ttgis.education.entity.ScormCourse;
import com.ttgis.education.service.LMSManifestHandler;
import com.ttgis.education.service.LMSPackageHandler;
import com.ttgis.education.utils.FileUtil;

/**
 * Scorm课件管理
 * @author Administrator
 *
 */
@Controller
public class ScormCourseController {
	@Resource
	LMSManifestHandler myManifestHandler;
	
	@Resource
	LMSPackageHandler myPackageHandler;
	//LMSManifestHandler myManifestHandler;
	//@Autowired
	//ICourseInfoService courseInfoService;
	
	@RequestMapping("scormCourseImportPage")
	public String scormCourseImportPage(HttpServletRequest request,ModelMap modelMap,HttpSession session){
		
		//List<CourseInfo> courseList = courseInfoService.findAllCourseinfo();
		
		//modelMap.addAttribute("courseList", courseList);
		
		return "/admin/course/courseImport";
	}
	/**
	 * �γ̵������
	 * @throws IOException 
	 */
	@RequestMapping("scormCourseImportAction")
	//public String courseImportAction(@RequestParam("id") int id, @RequestParam("scormZipFile") MultipartFile scormZipFile,HttpServletRequest request,ModelMap modelMap,HttpSession session) throws IOException{
	public void scormCourseImportAction(@RequestParam(value="file") MultipartFile file,Courseresource n ,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession session){
		try{
			PrintWriter out = null;
			out = response.getWriter();
			//sessionId
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				System.out.println("filename:  "+fileName.substring(fileName.lastIndexOf(".")));
				if(fileName.indexOf(".")!=-1){
					if(!fileName.substring(fileName.lastIndexOf(".")).equals(".zip")){//格式不正确
						out.print("<script>alert('文件格式不正确，scorm课件只支持zip格式课件')</script>");
						out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
						return;
					}
				}else{
					out.print("<script>alert('文件格式不正确，scorm课件只支持zip格式课件')</script>");
					out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
					return;
				}
			}
			
			//String sessionID  = request.getSession().getId();
			
			String sessionID = UUID.randomUUID().toString().replace("-", "");
	
			String theWebPath = request.getServletContext().getRealPath("/");//��þ��·��
	
			String uploadDir = "C:\\SampleRTEFiles\\tempUploads\\" + sessionID;
			File theRTEUploadDir = new File(uploadDir);
	
			if (!theRTEUploadDir.isDirectory()) {
				theRTEUploadDir.mkdirs();
			}
			
	
			//String fileName = scormZipFile.getOriginalFilename();
			String fileName = file.getOriginalFilename();
			
			String zipFile = uploadDir + "\\" + fileName;		
			
			//FileUtil.copy(scormZipFile.getInputStream(), new File(zipFile));
			FileUtil.copy(file.getInputStream(), new File(zipFile));
			
			try{
				myPackageHandler.extract(zipFile, "imsmanifest.xml", uploadDir);
			}catch(Exception e){
				//e.printStackTrace();
				out.print("<script>alert('上传文件不符合scorm课件标准')</script>");
				out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
				return;
			}
			
			String manifestFile = uploadDir + "\\" + "imsmanifest.xml";
			InputSource fileToParse = this.setUpInputSource(manifestFile);
			
			//课件编号
			String courseID = n.getCourseId();//// Get the course ID
			String courseRoot = theWebPath + "UploadFile\\CourseImports\\" + courseID;
			File rootFile = new File(courseRoot);
			int scoNo;
			if(!rootFile.exists()){
				rootFile.mkdirs();
				scoNo=1;
				myManifestHandler.setScosNo(scoNo);
			}else{
				scoNo=rootFile.list().length+1;
				myManifestHandler.setScosNo(scoNo);
			}
			
			//myManifestHandler.setCourseId(id);
			myManifestHandler.setCourseID(n.getCourseId());
			myManifestHandler.setCourseResourceName(n.getCourseresourceName());
			myManifestHandler.setFileToParse(fileToParse);
			myManifestHandler.setControl("");
			myManifestHandler.setIsTest("1");
			myManifestHandler.setAccount((Account) session.getAttribute("sessionUser"));
			
			// Parse the manifest and fill up the object structure
			boolean result = myManifestHandler.processManifest();
			if(result){
				System.out.println("抽取成功");
			}else{
				System.out.println("抽取失败");
				out.print("<script>alert('资源信息保存失败')</script>");
				out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
				return;
			}
			
			
			ZipFile archive = new ZipFile(zipFile);
			// do our own buffering; reuse the same buffer.
			byte[] buffer = new byte[16384];
			// Loop through each Zip file entry
			for (Enumeration e = archive.entries(); e.hasMoreElements();) {
				// get the next entry in the archive
				ZipEntry entry = (ZipEntry) e.nextElement();
				if (!entry.isDirectory()) {
					// Set up the name and location of where the file will be 
					// extracted to
					String filename = entry.getName();
					filename = filename.replace('/', java.io.File.separatorChar);
					filename = theWebPath + "UploadFile\\CourseImports\\" + courseID + "\\"+scoNo+"\\"+ filename;
					File destFile = new File(filename);
	
					String parent = destFile.getParent();
					if (parent != null) {
						java.io.File parentFile = new java.io.File(parent);
						if (!parentFile.exists()) {
							// create the chain of subdirs to the file
							parentFile.mkdirs();
						}
					}
					// get a stream of the archive entry's bytes
					InputStream in = archive.getInputStream(entry);
					// open a stream to the destination file
					OutputStream outStream = new FileOutputStream(filename);
					// repeat reading into buffer and writing buffer to file,
					// until done.  count will always be # bytes read, until
					// EOF when it is -1.
					int count;
					while ((count = in.read(buffer)) != -1){
						outStream.write(buffer, 0, count);
					}
	
					in.close();
					outStream.close();
				}
			}
			
			// Write the Sequencing Object to a file
			String sequencingFileName = theWebPath + "UploadFile\\CourseImports\\"+ courseID+ "\\"+scoNo+"\\"+  "\\sequence.obj";
			File sequencingFile = new File(sequencingFileName);
			FileOutputStream ostream = new FileOutputStream(sequencingFile);
			ObjectOutputStream oos = new ObjectOutputStream(ostream);
			oos.writeObject(myManifestHandler.getOrgsCopy());
			oos.flush();
			oos.close();
	
			// Delete uploaded files
			boolean wasdeleted = false;
			File uploadFiles[] = theRTEUploadDir.listFiles();
			for (int i = 0; i < uploadFiles.length; i++) {
				uploadFiles[i].deleteOnExit();
			}
			theRTEUploadDir.deleteOnExit();
			//return "success";
			
			out.print("<script>alert('保存成功')</script>");
			out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
			//return "/admin/course/courseImport";
		}catch(IOException e){
		
		}
	}
	
	/****************************************************************************
	**
	** Function:  setUpInputSource()
	** Input:   fileName - String
	** Output:  is - InputSource
	**
	** Description:  This function returns the input source.
	**
	***************************************************************************/
	private InputSource setUpInputSource(String fileName)
	{
	   
	   InputSource is = new InputSource();
	   is = setupFileSource(fileName);
	   return is;
	} 
	
	/****************************************************************************
	**
	** Function:  setUpFileSource()
	** Input:   fileName - String
	** Output:  is - InputSource
	**
	** Description:  This function returns the input source.
	**
	***************************************************************************/
	private InputSource setupFileSource(String filename)
	{
	   try
	   {
	      File xmlFile = new File( filename );
	      if ( xmlFile.isFile() )
	      {
	    	 InputStreamReader fr = new InputStreamReader(new FileInputStream( xmlFile),"UTF-8" );//UTF-8����
	         InputSource is = new InputSource(fr);
	         return is;
	      }
	      else
	      {
	    	  System.out.println(filename+" ������");
	      }    
	   }
	   catch ( NullPointerException  npe )
	   {
	      System.out.println( "Null pointer exception" + npe); 
	   }
	   catch ( SecurityException se )
	   {
	      System.out.println( "Security Exception" + se); 
	   }
	   catch ( FileNotFoundException fnfe )
	   {
	      System.out.println("File Not Found Exception" + fnfe);
	   }catch(UnsupportedEncodingException e){
		   System.out.println("Unsupported Encoding Exception" + e);
	   }
	   return new InputSource();
	}
	
	
	/**
	 * �γ̲���ҳ��
	 */
	@RequestMapping("scormPalyerIndex")
	public String scormPlayerIndex(@ModelAttribute ScormCourse scormCourse,HttpServletRequest request,ModelMap modelMap,HttpSession session){
		//int courseId = Integer.valueOf(request.getParameter("courseId"));
		//int scoId = Integer.valueOf(request.getParameter("scoId"));
		
		modelMap.addAttribute("src", "http://localhost:8080/edu/htdoc/CourseImports/1/shared/launchpage.html");
		
		modelMap.addAttribute("courseId", "1");
		modelMap.addAttribute("scoId", "1");
		
		
		return "/scormPlayer/index";
	}
	
	/**
	 * �γ̲�����
	 */
	@RequestMapping("scormPalyer")
	public String scormPlayer(@ModelAttribute ScormCourse scormCourse,HttpServletRequest request,ModelMap modelMap,HttpSession session){
		int courseId = Integer.valueOf(request.getParameter("courseId"));
		int scoId = Integer.valueOf(request.getParameter("scoId"));
		
		System.out.println("courseId:  "+courseId);
		System.out.println("scoId:  "+scoId);
		
		modelMap.addAttribute("src", "http://localhost:8080/edu/htdoc/CourseImports/34/course.html");
		
		
		return "/scormPlayer/player";
	}
	
	
	/**
	 * ����ajax���
	 */
	@RequestMapping("ajaxs")
	public void ajaxData(HttpServletRequest request){
		System.out.println("scorm  ===="+request.getParameter("usnername"));
		System.out.println("scorm  ===="+request.getParameter("date"));
	}
	
	
	
	
}
