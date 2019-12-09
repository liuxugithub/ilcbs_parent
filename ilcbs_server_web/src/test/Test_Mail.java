/*
import godday.xin.utils.MailUtil;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;

public class Test_Mail {
    @Test
    public void sendMail() throws MessagingException, FileNotFoundException, UnsupportedEncodingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.163.com");
        properties.put("mail.smtp.auth","true");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        //准备邮件
        MimeMessage message = new MimeMessage(session);
        //发件人
        InternetAddress internetAddress = new InternetAddress("16602806242@163.com");
        message.setFrom(internetAddress);
        //收件人
        InternetAddress internetAddress_accept=new InternetAddress("504741809@qq.com");
        message.setRecipient(Message.RecipientType.TO,internetAddress_accept);
        //主题
        Multipart multipart = new MimeMultipart();
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent("hello,world", "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        File file = new File("/Users/liuxu/Desktop/ojdbc7.jar");
        DataSource dataSource=new FileDataSource(file);
        BodyPart attachment=new MimeBodyPart();
        attachment.setDataHandler(new DataHandler(dataSource));
        attachment.setFileName(MimeUtility.encodeWord(file.getName()));
        multipart.addBodyPart(attachment);
        message.setContent(multipart);
        message.setSubject("手动发送");
        message.saveChanges();
        Transport tsp= session.getTransport("smtp");
        tsp.connect("smtp.163.com","16602806242@163.com","a123456");
        tsp.sendMessage(message, message.getAllRecipients());
        System.out.println("filename:"+file.getName());
        tsp.close();
    }
}
*/
