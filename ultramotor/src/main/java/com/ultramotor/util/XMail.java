package com.ultramotor.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author nghipc
 */
public class XMail {

    //create a session to send mail
    private static Session getSession() {
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", 587);
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        
        return Session.getInstance(p, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ultramotorsystem@gmail.com", "Ultramotor123");
            }
        });
    }

    /**
     * Gửi mail tới một địa chỉ, không có file attachment
     *
     * @param email địa chỉ email người nhận
     * @param content nội dung email
     * @param subject tiêu đề mail
     */
    public static void sendMail(String email, String content, String subject) {
        sendMail(email, content, subject, null);
    }

    public static void sendMail(String email, String content, String subject, File file) {
        sendMail(Collections.singletonMap(email, content), subject, file);
    }

    /**
     * Gửi mail tới nhiều người với nội dung tương ứng, có file đính kèm
     *
     * @param map Key: email address, Value: mail content
     * @param subject tiêu đề mail
     * @param file attachment file
     */
    public static void sendMail(Map map, String subject, File file) {
        //tạo session mới
        Session s = getSession();

        //tạo thư 
        map.forEach((mail, content) -> {
            try {
                Message msg = new MimeMessage(s);
                //người nhận
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse((String) mail));

                //tiêu để mail
                msg.setSubject(subject);

                //nội dung mail
                //nếu có attachment
                if (file != null) {
                    //set text
                    MimeBodyPart main = new MimeBodyPart();
                    main.setContent(content, "text/html; charset=utf-8");
                    //set attachment
                    MimeBodyPart attachment = new MimeBodyPart();
                    attachment.attachFile(file);
                    //set mail
                    MimeMultipart parts = new MimeMultipart();
                    parts.addBodyPart(main);
                    parts.addBodyPart(attachment);
                    msg.setContent(parts);
                } else {
                    //set mail
                    msg.setContent(content, "text/html; charset=utf-8");
                }
                //gửi mail
//                System.out.println("Sending");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Transport.send(msg);
                        } catch (MessagingException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();

            } catch (MessagingException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
