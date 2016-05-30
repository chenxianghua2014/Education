package com.ttgis.education.service;

/*******************************************************************************
 ** 
 ** Filename:  LMSManifestHandler.java
 **
 ** File Description:  This file extends from CPDOMParser.  It uses the parser
 **                    in a way unique to the Sample RTE.  All relevant data
 **                    from the manifest file is written to a corresponding
 **                    database after it is accessed.
 **
 ** Author:  ADLI Project
 **
 ** Company Name: CTC
 **
 ** Module/Package Name: org.adl.lms.server
 ** Module/Package Description:
 **
 ** Design Issues:
 **
 ** Implementation Issues:
 ** Known Problems:
 ** Side Effects:
 **
 ** References:  SCORM
 **
 *******************************************************************************
 **
 ** Concurrent Technologies Corporation (CTC) grants you ("Licensee") a non-
 ** exclusive, royalty free, license to use, modify and redistribute this
 ** software in source and binary code form, provided that i) this copyright
 ** notice and license appear on all copies of the software; and ii) Licensee
 ** does not utilize the software in a manner which is disparaging to CTC.
 **
 ** This software is provided "AS IS," without a warranty of any kind.  ALL
 ** EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 ** IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-
 ** INFRINGEMENT, ARE HEREBY EXCLUDED.  CTC AND ITS LICENSORS SHALL NOT BE LIABLE
 ** FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 ** DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES.  IN NO EVENT WILL CTC  OR ITS
 ** LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 ** INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 ** CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 ** OR INABILITY TO USE SOFTWARE, EVEN IF CTC HAS BEEN ADVISED OF THE POSSIBILITY
 ** OF SUCH DAMAGES. 
 **
 *******************************************************************************
 **
 ** Date Changed   Author of Change  Reason for Changes
 ** ------------   ----------------  -------------------------------------------
 **
 *******************************************************************************/

// ADL imports
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.annotation.Resource;

import org.adl.parsers.dom.ADLItem;
import org.adl.parsers.dom.ADLOrganizations;
import org.adl.parsers.dom.CPDOMParser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Courseresource;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Record;
import com.ttgis.education.mapper.CourseresourceMapper;
import com.ttgis.education.mapper.LogMapper;
import com.ttgis.education.mapper.RecordMapper;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.Tools;

/*import com.edu.model.CourseInfo;
import com.edu.model.ScoInfo;
import com.edu.model.UserInfo;
import com.edu.service.ICourseInfoService;
import com.edu.service.IScoInfoService;
*/
@Repository
@Service
public class LMSManifestHandler extends CPDOMParser {
	
	@Resource
	private CourseresourceMapper courseresourceMapper;
	
	@Resource
	private RecordMapper recordMapper;
	
	@Resource
	private LogMapper logMapper;
	
	private static boolean _Debug = true;

	int flag = 0;//标志本次上传的课件
	
	
	int scosNo=0;//课件编号
	
	protected Vector items;
	private Vector tempItemList;
	protected InputSource sourceToParse;
	//protected String courseId;
	protected String courseDir;

	protected String courseID;
	protected String control;
	protected String isTest;
	protected String courseResourceName;
	protected Account account;


	/**************************************************************************
	 ** 
	 ** Method: LMSManifestHandler() Input: None
	 ** 
	 ** Output: None
	 ** 
	 ** Description: Default Constructor.
	 ** 
	 ***************************************************************************/
	public LMSManifestHandler() {
		super();
		tempItemList = new Vector();
		items = new Vector();
		courseDir = "";
		courseID = "";
		control = "";
	}

	/****************************************************************************
	 ** 
	 ** Method: processManifest() 
	 *  Input: None 
	 *  Output: boolean - Whether or not
	 *  the method was successful Description: This method initiates the RTE
	 *  import/parsing of a manifest file from a PIF content package.
	 ** 
	 ** Side Effects: none
	 ** 
	 ****************************************************************************/
	public boolean processManifest() {
		boolean valid = false;
		if (_Debug) {
			System.out.println("Entering LMSManifestHandler::processManifest()");
		}

		try {
			// Parse the file
			parse(sourceToParse);

			if (_Debug) {
				System.out.println("Document parsing complete.");
			}
			System.out.println("1----document="+document);
			document = getDocument();
			System.out.println("2----document="+document);
			valid = true;

			// Call process content
			processContent();
		} catch (SAXException se) {
			String message = "Parser threw a SAXException.";
			System.out.println(message);
		} catch (IOException ioe) {
			String message = "Parser threw a IOException.";
			System.out.println(message);
		}

		if (_Debug) {
			System.out.println("Exiting LMSManifestHandler::processManifest()");
		}

		// Write to the corresponding database
		updateDB();
		// Return boolean signifying whether or not the parsing was successful
		return valid;
	}

	/****************************************************************************
	 ** 
	 ** Method: processContent() 
	 *  Input: none 
	 *  Output: boolean - Whether the method was successful or not. Description: This method starts at the root node
	 *          and then calls calls fillManifest which populates the parser structure.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public boolean processContent() {
		if (_Debug) {
			System.out.println("*** LMSManifestHandler::processContent() ***");
		}

		// boolean that signifies whether or not the method call was successful
		boolean result = true;

		if (_Debug) {
			if (document == null) {
				System.out.println("the document is null");
			}
		}
		System.out.println("-------document="+document+"|---");
		// Get the root node of the imsmanifest <manifest>
		Node contentNode = document.getDocumentElement();

		// Get the children of the root node
		NodeList contentChildren = contentNode.getChildNodes();

		if (_Debug) {
			System.out.println("*** NODE: " + contentNode.getNodeName());
			System.out.println("*** Children - " + contentChildren.getLength()+ " ***");
		}

		// Call fillManifest(). This will fill the parser structure
		this.manifest.fillManifest(contentNode);

		// Set the control attribute
		// *************************************************
		// This is proprietary in version 1.2 of the RTE
		// This will change!
		// *************************************************
		this.setSequence();

		// Uses the newly populated structure to populate the
		// items Vector.
		System.out.println("this.items size          "+this.items.size());
		
		this.items = this.getItemList();
		
		System.out.println("this.items size          "+this.items.size());

		// Get resources and compare w/ the item Vector
		for (int i = 0; i < contentChildren.getLength(); i++) {
			Node currentNode = contentChildren.item(i);

			// Make sure this is an "element node"
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				// Move to the resources element to process the resources
				if (currentNode.getNodeName().equalsIgnoreCase("resources")) {
					if (_Debug) {
						System.out.println("*** Found Current Node: "+ currentNode.getNodeName());
					}

					// process the Resources section.
					result = processResources(currentNode);
					if (result == false) {
						break;
					}
				}

			}
		}

		if (_Debug) {
			System.out
					.println("*** Exiting LMSManifestHandler::processContent() ***");
			System.out.println("*** Returning: " + result);
		}
		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: processResources Input: Node resourcesNode - The <resources> node
	 * from the imsmanfest file Output: Description: This method populates the
	 * resources section of the parser structure.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public boolean processResources(Node resourcesNode) {

		boolean result = true;
		int level = 1;

		if (_Debug) {
			System.out.println("******  LMSManifestHandler:processResources  *********");
		}

		if (document == null) {
			System.out.println("the document is null");
		}

		// Get the children of the resources node
		NodeList resourcesChildren = resourcesNode.getChildNodes();

		if (_Debug) {
			System.out.println("*** NODE: " + resourcesNode.getNodeName());
			System.out.println("*** Children - "+ resourcesChildren.getLength() + " ***");
		}

		// Find the resource node(s)
		for (int i = 0; i < resourcesChildren.getLength(); i++) {
			Node currentNode = resourcesChildren.item(i);

			// Make sure this is an "element node"
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equalsIgnoreCase("resource")) {
					if (_Debug) {
						System.out.println("*** Found Current Node: "+ currentNode.getNodeName());
					}

					result = processResource(currentNode);
					if (result == false) {
						break;
					}
				}
			}
		}

		if (_Debug) {
			System.out.println("*** Exiting LMSManifestHandler::processResources() ***");
			System.out.println("*** Returning: " + result);
		}

		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: processResource() Input: Node resourceNode - A <resource> node
	 * from the imsmanifest file Output: Description: This method populates the
	 * resource section of the parser object.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public boolean processResource(Node resourceNode) {
		boolean result = true;

		if (_Debug) {
			System.out.println("******  LMSManifestHandler:processResource  *********");
		}

		String id = getAttribute(resourceNode, "identifier");
		String scormType = getAttribute(resourceNode, "adlcp:scormtype");
		String type = getAttribute(resourceNode, "type");
		String href = getAttribute(resourceNode, "href");

		updateItemList(id, scormType, type, href);

		if (_Debug) {
			System.out.println("*** Exiting LMSManifestHandler::processResource() ***");
			System.out.println("*** Returning: " + result);
		}

		return result;

	}

	/****************************************************************************
	 ** 
	 ** Method: updateItemList Input: String id - the identifier of the item
	 * String scormType - the scormtype of the item (or reference) String type -
	 * the type attribute String href - the href location Output: Description:
	 * This method adds the launch location to the item vector. The launch is
	 * not part of the <item> element, so this method compares the identifier
	 * and identifierref and when a match is found, the item section of the
	 * parser object is populated with the resource launch location. This is
	 * done only for convenience.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public void updateItemList(String id, String scormType, String type,
			String href) {
		if (_Debug) {
			System.out.println("******  LMSManifestHandler:updateItemList  *********");
		}

		ADLItem tempItem = new ADLItem();

		// Loop throught the item vector checking the identfierref with the
		// identifier of the resource. If they match, update the item and exit
		for (int i = 0; i < items.size(); i++) {
			tempItem = (org.adl.parsers.dom.ADLItem) this.items.elementAt(i);
			String idref = tempItem.getIdentifierref();

			if (idref.equals(id)) {
				if (_Debug) {
					System.out.println("****** !!  Found Matching Resource  !!  *****");
					System.out.println("i is:" + i + " -- Items size is"+ items.size());
				}

				if (type != null && !type.equals("")) {
					tempItem.setType(type);
				}
				if (scormType != null && !type.equals("")) {
					tempItem.setScormType(scormType);
				}
				if (href != null && !href.equals("")) {
					tempItem.setLaunchLocation(href);
				}
				items.removeElementAt(i);
				items.insertElementAt(tempItem, i);
			}
		}

		if (_Debug) {
			System.out.println("****** Exiting - LMSManifestHandler:updateItemList  *********");
		}

	}

	/****************************************************************************
	 ** 
	 ** Method: getCourseID() Input: none Output: String - this course id.
	 * Description: This is a getter method on the courseID member.
	 ** 
	 ****************************************************************************/
	public void setCourseID(String courseID) {
		this.courseID=courseID;
	}
	
	public String getCourseID() {
		return this.courseID;
	}

	/****************************************************************************
	 ** 
	 ** Method: getOrgsCopy() Input: none Output: ADLOrganizations Description:
	 * This method was developed anticipating sequencing for version 1.3. This
	 * method will return the entire course <organization> information. This
	 * will include all sequencing information.
	 ** 
	 ****************************************************************************/
	public ADLOrganizations getOrgsCopy() {
		return this.manifest.getOrganizations();
	}

	/****************************************************************************
	 ** 
	 ** Method: setSequence() Input: none Output: none Description: This method
	 * will be used to set the control type when sequencing is introduced in
	 * version 1.3.
	 ** 
	 ****************************************************************************/
	public void setSequence() {
		if (_Debug) {
			System.out.println("In LMSManifestHandler:setSequence() ");
		}
		// ****************************************************************
		// This is being commented out due to the lack of a sequencing spec
		// in version 1.2. It will be used in the next version
		// ****************************************************************
		// this.control =
		// this.manifest.getOrganizations().getFirstOrg().getSequence().getControl();

		if (_Debug) {
			System.out.println("Control is: " + this.control);
		}
	}

	

	public void setControl(String controlType) {
		this.control = controlType;
	}

	public void setFileToParse(InputSource inputStream) {
		this.sourceToParse = inputStream;
	}

	/****************************************************************************
	 ** 
	 ** ���γ���Ϣд�����
	 ** 
	 ** 
	 ***************************************************************************/
	protected void updateDB() {
		if (_Debug) {
			System.out.println("******  LMSManifestHandler:updateDB()  *********");
		}
		try {

			System.out.println("flag   "+flag);

			ADLItem tempItem = new ADLItem();
			
			for (int i = flag; i < items.size(); i++) {
				Courseresource scoInfo = new Courseresource();
				tempItem = (org.adl.parsers.dom.ADLItem) items.elementAt(i);

				// Decode the URL before inserting into the database
				URLDecoder urlDecoder = new URLDecoder();
				String alteredLocation = new String();

				// If its external, don't concatenate to the local Web root.
				if ((tempItem.getLaunchLocation().startsWith("http://"))|| (tempItem.getLaunchLocation().startsWith("https://"))) {
					alteredLocation = URLDecoder.decode(tempItem.getLaunchLocation());
				} else {
					// Create the altered location (with decoded url)
					alteredLocation = "UploadFile/CourseImports/"
							+ courseID
							+ "/"
							+ this.scosNo
							+ "/"
							+ URLDecoder.decode(tempItem.getLaunchLocation());
				}
				
				if(alteredLocation.indexOf(".html")==-1){
					continue;
				}
				// Insert into the database
				
				//Timestamp uploadTime = new Timestamp(System.currentTimeMillis());
				Date uploadTime = new Date();
				System.out.println("isTest="+isTest);
				
				scoInfo.setCourseresourceId(Tools.getGUID());
				scoInfo.setCourseId(courseID);
				scoInfo.setCourseresourceDel(1);
				scoInfo.setCourseresourceAt(new Date());
				scoInfo.setCourseresourceType("6");
				scoInfo.setCourseresourceFileaddr(alteredLocation);
				scoInfo.setCourseresourceMark(2);
				scoInfo.setCourseresourceFiletype("scorm");
				scoInfo.setCourseresourceFromname(courseResourceName);//独特的。。。。
				scoInfo.setCourseresourceName(courseResourceName+": "+tempItem.getTitle());//课件名称
				
				System.out.println("CourseresourceId:"+scoInfo.getCourseresourceId()+"   CourseId"+scoInfo.getCourseId()+"     CourseresourceFileaddr"+alteredLocation);
				int j = courseresourceMapper.insertSelective(scoInfo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				if(j>0){
					Record record = new Record();
					record.setRecordAt(new Date());
					record.setRecordClass("课程视频");
					record.setRecordContentid(courseID);
					record.setRecordDel(1);
					record.setRecordId(Tools.getGUID());
					record.setRecordNote(account.getAccountName()+"上传课程视频"+courseID);
					record.setRecordTitle("课程视频上传");
					record.setRecordType("5");
					record.setStudentId(account.getAccountId());
					recordMapper.insertSelective(record);
					
					Log log = new Log();
					
					log.setLogAt(sdf.format(new Date()));
					log.setLogContent("用户："+account.getAccountLoginid()+"，上传视频:"+courseID);
					log.setLogId(Tools.getGUID());
					log.setLogIp("");//IP地址不记录先
					log.setLogUser(account.getAccountLoginid());
					log.setLogUserType("后台用户");
					logMapper.insertSelective(log);
				}
				
			
			} // Ends looping throught the item list


		} catch (Exception se) {
			se.printStackTrace();
		} 
		
		flag=this.items.size();//added by jiahua
	}

	/****************************************************************************
	 ** 
	 ** Method: getAttribute() Input: Node theNode - The current node that was
	 * traversed to String the Attribute - The name of the attribute that the
	 * value is desired for. Output: Description: This method
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	protected String getAttribute(Node theNode, String theAttribute) {
		String returnValue = new String();

		// grab attributes of the node
		Attr attrs[] = sortAttributes(theNode.getAttributes());

		// now see if the asked for attribute exists and send
		// back the value
		Attr attribute;
		for (int i = 0; i < attrs.length; i++) {
			attribute = attrs[i];

			if (attribute.getName().equals(theAttribute)) {
				returnValue = attribute.getValue();
				break;
			}
		}

		return returnValue;
	}

	/****************************************************************************
	 ** 
	 ** Method: sortAttributes() Input: NamedNodeMap attrs - The list of
	 * attributes Output: Attr[] - Sorted array of attributes Description: This
	 * method returns an array of sorted attributes.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	protected Attr[] sortAttributes(NamedNodeMap attrs) {
		int len = (attrs != null) ? attrs.getLength() : 0;
		Attr array[] = new Attr[len];
		for (int i = 0; i < len; i++) {
			array[i] = (Attr) attrs.item(i);
		}
		for (int i = 0; i < len - 1; i++) {
			String name = array[i].getNodeName();
			int index = i;
			for (int j = i + 1; j < len; j++) {
				String curName = array[j].getNodeName();
				if (curName.compareTo(name) < 0) {
					name = curName;
					index = j;
				}
			}
			if (index != i) {
				Attr temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}

		return (array);

	}

	/****************************************************************************
	 ** 
	 ** Method: getSubElement() Input: Node node - The current node that has been
	 * traversed to String element - The name of the sub-element that's value is
	 * desired. Output: String - The value of the sub-element Description: This
	 * method gets the value of a desired sub-element. Side Effects:
	 ** 
	 ****************************************************************************/
	public String getSubElement(Node node, String element) {
		String value = new String();
		NodeList kids = node.getChildNodes();
		// cycle through all children of node to get the text
		if (kids != null) {
			for (int i = 0; i < kids.getLength(); i++) {
				if (kids.item(i).getNodeType() == Node.ELEMENT_NODE) {
					// look for the asked for element
					if (kids.item(i).getNodeName().equalsIgnoreCase(element)) {
						value = getText(kids.item(i));
						return value;
					}
				}
			}
		} else {
			if (_Debug) {
				System.out.println("node has no kids");
			}
		}
		return value;
	}

	/****************************************************************************
	 ** 
	 ** Method: getText() Input: Node node - The current node Output: String -
	 * The text of the desired node. Description: This method gets the child
	 * text node of the node that is passed in for input. Side Effects:
	 ** 
	 ****************************************************************************/
	public String getText(Node node) {
		String value = new String();
		NodeList kids = node.getChildNodes();
		// cycle through all children of node to get the text
		if (kids != null) {
			for (int i = 0; i < kids.getLength(); i++) {
				// make sure we have a text element
				if ((kids.item(i).getNodeType() == Node.TEXT_NODE)
						|| (kids.item(i).getNodeType() == Node.CDATA_SECTION_NODE)) {
					value = value + kids.item(i).getNodeValue().trim();
				}
			}
		} else {
			if (_Debug) {
				System.out.println("node has no kids");
			}
		}

		return value;
	}

	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
	
	public String getCourseResourceName() {
		return courseResourceName;
	}

	public void setCourseResourceName(String courseResourceName) {
		this.courseResourceName = courseResourceName;
	}
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getScosNo() {
		return scosNo;
	}

	public void setScosNo(int scosNo) {
		this.scosNo = scosNo;
	}

}

