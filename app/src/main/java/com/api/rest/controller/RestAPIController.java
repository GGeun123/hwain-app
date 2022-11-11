package com.api.rest.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.api.rest.dao.RestAPIService;

@SuppressWarnings("unchecked")
@Controller
@Path("/Rest")
@RequestMapping("/Rest")
public class RestAPIController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
    JSONObject globalJsonObject = new JSONObject();
    
    // The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information (no kidding).
    @Context
    UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    @Context
    HttpServletRequest request;
     
    // Basic "is the service running" test
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
      return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }  
 
    @GET
    @Path("sample/skey={skey}")
    @Produces(MediaType.TEXT_HTML)
//    public Viewable getSource(@PathParam("skey") final String skey) {
//    	Map<String, Object> dataMap = DataMap.getDataMap(request);
//    	Map<String, Object> model = new HashMap<String, Object>();
//    	dataMap.put("skey", skey);
//        
//    	Map<String, Object> map= null;
//    	try {
//    		map= restAPIService.mapDataControl("test", dataMap);
//    		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}        
//        
//        model.put("title", "Hello!");
//        model.put("message", "Hello, World!");
//        model.put("map", map);
//        
//        return new Viewable("/WEB-INF/jsp/test.jsp", model);
//    } 
    
    //사번 사용자 정보
//    @GET
//    @Path("userInfos/user_sabun={user_sabun}")
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    public Response getUserInfo(@PathParam("user_sabun") final String user_sabun) {
//    	Map<String, Object> dataMap = DataMap.getDataMap(request);
//    	System.out.println("userInfos user_sabun : "+user_sabun);
//    	dataMap.put("user_sabun", user_sabun);
//    	
//    	JSONObject jsonObject = new JSONObject();
//    	JSONArray array = new JSONArray();
//    	
//    	try {
//			Map<String, Object> user_infoMap=restAPIService.mapDataControl("user_infos", dataMap);	
//			if(user_infoMap!=null){
//	    		array.add(user_infoMap);
//	    		jsonObject.put("datas", array);
//	    		jsonObject.put("status", "success");
//			}else{
//				jsonObject.put("status", "failed"); 
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			jsonObject.put("status", "failed");
//			throw new WebApplicationException(Response.Status.NOT_FOUND);
//		}    
//        
//    	return Response.status(200).entity(jsonObject).build();
//    }     
//    
    //로그인 체크
//    @GET
//    @Path("loginCheck/user_sabun={user_sabun}/user_pw={user_pw}")
//    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//    public Response loginDataCheck(@PathParam("user_sabun") final String user_sabun, @PathParam("user_pw") final String user_pw) {
//    	Map<String, Object> dataMap = DataMap.getDataMap(request);
//    	System.out.println("list user_sabun : "+user_sabun);
//    	dataMap.put("user_sabun", user_sabun);
//    	dataMap.put("user_pw", user_pw);
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	JSONObject jsonObject = new JSONObject();
//    	JSONArray array = new JSONArray();
//    	
//        int result = 0;
//        try{
//            result = restAPIService.login_check(dataMap);
//            System.out.println((new StringBuilder("로그인 처리,result=")).append(result).toString());   
//    		jsonObject.put("status", "success");        
//    		jsonObject.put("result", result);	
//        }
//        catch(SQLException e){
//            System.out.println("로그인 처리 실패");
//			e.printStackTrace();
//			jsonObject.put("status", "failed");
//			throw new WebApplicationException(Response.Status.NOT_FOUND);            
//        }
//        
//        if(result == RestAPIService.LOGIN_OK){
//        	try {
//				Map<String, Object> user_infoMap=restAPIService.mapDataControl("user_infos", dataMap);	
//	    		array.add(user_infoMap);
//	    		jsonObject.put("datas", array);
//	
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}         
//            
//        }else if(result == RestAPIService.PWD_MISMATCH){
//        	System.out.println("PWD_MISMATCH");
//        	jsonObject.put("datas", array);
//            
//        }else if(result == RestAPIService.NONE_ID){
//        	System.out.println("NONE_ID");
//        	jsonObject.put("datas", array);
//        }
//        
//    	return Response.status(200).entity(jsonObject).build();
//    }
      

    
    @RequestMapping(value="gps_test.do",method=RequestMethod.GET)
    public ModelAndView getBoardData(HttpServletRequest request, HttpServletResponse response) {
    	
    	ModelAndView mv = new ModelAndView();

        mv.setViewName("/safe/gps");
        return mv;
    }    
    
    @RequestMapping("sendMail.do")
    public void MailExam() throws Exception{
        
        Properties props = new Properties(); 
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "mail2.knlsys.net"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.port", "25"); 
        props.put("mail.smtp.socketFactory.port", "25"); 
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
        props.put("mail.smtp.socketFactory.fallback", "false"); 
        props.setProperty("mail.smtp.quitwait", "false"); 
         
        Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() { 
                return new PasswordAuthentication("sjis00", "sj#isgw!!"); 
            }
        };
    
        Session session = Session.getDefaultInstance(props,auth);
        session.setDebug(true); // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
         
        MimeMessage message = new MimeMessage(session); 
        message.setSender(new InternetAddress("sjis00@mail2.knlsys.net")); 
        message.setSubject("test"); 
 
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("mlovesm@sejoongis.co.kr")); 
         
        Multipart mp = new MimeMultipart();
        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText("Test Contents");
        mp.addBodyPart(mbp1);
         
        message.setContent(mp);
         
        Transport.send(message);
    }
    
    @GET
    @Path("sendMail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMail() {    	
    	List<String> list = new ArrayList<String>();
    	list.add("mlovesms@gmail.com");
    	list.add("mlovesm@naver.com");
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
			send(list, "sjis00@mail2.knlsys.net", "mail2.knlsys.net" , "25",
					"내용", "테스트3", "sjis00" , "sj#isgw!!");

    		jsonObject.put("datas", list);
    		jsonObject.put("count", list.size());
    		jsonObject.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }
    
	public void send(List<String> toMail, String fromMail, String host, String port,
			String message, String title, String id, String pwd)throws Exception{

		Properties p = new Properties();

//		p.put("mail.smtp.user", user);
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.port", port);
		p.put("mail.smtp.starttls.enable","true");
		p.put( "mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", port); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		p.put("mail.smtp.socketFactory.fallback", "false");    
		
		Authenticator auth = new SMTPAuthenticator(id,pwd);
		Session session = Session.getInstance(p, auth);
//		session.setDebug(true); // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
		
		MimeMessage msg = new MimeMessage(session);
		msg.setSubject(title,"UTF-8");

		Address fromAddr = new InternetAddress(fromMail); // 보내는 사람의 메일주소
		msg.setFrom(fromAddr);

		  InternetAddress[] addressTo = new InternetAddress[toMail.size()];
		  for (int i = 0; i < toMail.size(); i++) {
			  addressTo[i] = new InternetAddress(toMail.get(i));
		  }
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		
        // 파일첨부를 위한 Multipart
        Multipart multipart = new MimeMultipart();                
         
        // 2. 파일을 첨부한다.
        String filePath = "C:/Users/GS/Desktop/SM6.jpg";
        File file = new File(filePath);
        
        if(file != null){
            if(fileSizeCheck(filePath)){
                MimeBodyPart bodyPart = new MimeBodyPart();
                bodyPart.setText(message);
                
                // 1. Multipart에 BodyPart를 붙인다.
                multipart.addBodyPart(bodyPart);
                
                // 2. 이미지를 첨부한다.
                bodyPart = new MimeBodyPart();               
                DataSource source = new FileDataSource(file);
                bodyPart.setDataHandler(new DataHandler(source));
                bodyPart.setFileName(MimeUtility.encodeText(source.getName(), "UTF-8", "B"));
                //Trick is to add the content-id header here
                bodyPart.setHeader("Content-ID", "image_id");
                multipart.addBodyPart(bodyPart);
                
                
                //third part for displaying image in the email body
//                bodyPart = new MimeBodyPart();
//                bodyPart.setContent("<img src='cid:image_id'>", "text/html");
//                multipart.addBodyPart(bodyPart);                
            }else{
                throw new Exception("file size overflow !");
            }
        }              
        
//        msg.setContent(multipart, "text/html;charset=utf-8");
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
//        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
//        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
//        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
        
        msg.setContent(multipart);
		Transport.send(msg);

	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		String id;
		String pwd;
		
		SMTPAuthenticator(String id , String pwd){
			this.id = id;
			this.pwd = pwd;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pwd); //구글아이디는 구글계정에서 @이후를 제외한 값이다. (예: abcd@gmail.com --> abcd)

		}

	}
	
    protected boolean fileSizeCheck(String filename) {
        if (new File(filename).length() > (1024 * 1024 * 2.5)) {
            return false;
        }
        return true;
    }  
	
}//c
