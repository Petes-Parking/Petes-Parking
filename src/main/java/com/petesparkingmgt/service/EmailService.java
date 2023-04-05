package com.petesparkingmgt.service;

import com.petesparkingmgt.dto.user.FriendDTO;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    public void createParkingPalRequestEmail(FriendDTO request) throws NoSuchAlgorithmException, KeyManagementException {
        String senderFirst = request.getSenderFirstName();
        String senderLast = request.getSenderLastName();

        String recipientFirst = request.getRecipientFirstName();
        String recipientLast = request.getRecipientLastName();
        String recipientEmail = request.getRecipientEmail();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        String strTime = timeFormat.format(date);

        String subject = "[Pete’s Parking] You Received a Parking Pal Request!";
        String body = "Dear " + recipientFirst + " " + recipientLast + ",\n" +
                "\n" +
                "You just received a parking pal request on Pete’s Parking. Please read the following information about the request:\n" +
                "\n" +
                "Date Request Sent: " + strDate + "\n" +
                "\n" +
                "Time Request Sent: " + strTime + "\n" +
                "\n" +
                "User who Sent Request: " + senderFirst + " " + senderLast + "\n" +
                "\n" +
                "To accept or deny this request, you can log into Pete’s Parking and navigate to the Parking Pals section.\n" +
                "\n" +
                "Pete’s Parking Team" + "\n" +
                "petesparkingapp@gmail.com";
        sendEmail(recipientEmail, subject, body);
    }

    public void sendEmail(String recipient, String subject, String body) throws NoSuchAlgorithmException, KeyManagementException {
        // Set SSL context with TLSv1.2 protocol explicitly
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, null);

        // Set properties for mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // set the SSL/TLS protocol explicitly

        // Create mail session
        Session mySession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("petesparkingapp@gmail.com", "accuoanuljpemsuc");
                    }
                });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(mySession);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("petesparkingapp@gmail.com"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
