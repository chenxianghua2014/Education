<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" import="java.io.*,java.util.*,com.sun.image.codec.jpeg.*,java.awt.*,java.awt.image.*"%>

<%
String s = "";

int intCount = 0;

int width=93;//宽度

int height=35;//高度

char[] code=new char[]{'0','1','2','3','4','5','6','7','8','9',
                       'A','B','C','D','E','F','G','H','J','K','M','N','P','Q','R','S','T','U','V','W','X','Y','Z',
                       'a','b','c','d','e','f','g','h','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z'};

Random random = new Random();//创建一个随机数生成器类

// 随机产生codeCount数字的验证码。   
for (int i = 0; i < 4; i++){
	String strRand = String.valueOf(code[random.nextInt(code.length)]);//得到随机产生的验证码数字
	s=s+strRand;// 将产生的四个随机数组合在一起
} 

// 保存入session,用于与用户的输入进行比较.
// 注意比较完之后清除session.

session.setAttribute("rand", s);
out.clear();
response.addHeader("pragma","NO-cache");
response.addHeader("Cache-Control","no-cache");
response.addDateHeader("Expries",0);
response.setContentType("image/jpeg");
        

// 定义图像buffer   
BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
Graphics2D gra = image.createGraphics(); 

// 将图像填充为白色   
gra.setColor(Color.WHITE);   
gra.fillRect(0, 0, width, height);   
  
// 创建字体，字体的大小应该根据图片的高度来定。  
       
//字体对象构造方法public Font(String familyName,int style,int size)
//familyName字体名；字体名可以分成两大类：中文字体：宋体、楷体、黑体等；英文字体：Arial、Times New Roman等等；
//style风格。PLAIN普通字体，BOLD（加粗），ITALIC（斜体），Font.BOLD+ Font.ITALIC（粗斜体）
//size 大小

Font font = new Font("Times New Roman", Font.BOLD+Font.ITALIC, 26);
  
// 设置字体。   
gra.setFont(font);   

// 画边框。   
gra.setColor(new Color(random.nextInt(200),random.nextInt(200),random.nextInt(200)));   
gra.drawRect(0, 0, width - 1, height - 1); 
   
// 随机产生干扰线，使图象中的认证码不易被其它程序探测到。   
gra.setColor(Color.BLACK);   
for (int i = 0; i < 25; i++) {   
	int x = random.nextInt(width);   
	int y = random.nextInt(height);   
	int xl = random.nextInt(5);   
	int yl = random.nextInt(5);  
	gra.setColor(new Color(random.nextInt(200),random.nextInt(200),random.nextInt(200)));
	gra.drawLine(x, y, x + xl, y + yl);   
}   
      
// 输出数字
char c;

for (int i = 0; i < 4; i++) {
	c = s.charAt(i);
	gra.drawString(c+"", i * 22 + 4, 25); // 20为宽度，19为上下高度位置
}

OutputStream toClient = response.getOutputStream();
JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(toClient);
encoder.encode(image);

toClient.close();
out = pageContext.pushBody();
%>