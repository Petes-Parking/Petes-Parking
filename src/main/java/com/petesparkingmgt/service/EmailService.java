package com.petesparkingmgt.service;

import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.reports.ExpReportDTO;
import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import com.petesparkingmgt.dto.user.FriendDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
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
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
                "petesparkingpurdue@gmail.com";
        sendEmail(recipientEmail, subject, body);
    }

    public void createParkingPalAcceptedEmail(FriendDTO request) throws NoSuchAlgorithmException, KeyManagementException {
        String senderFirst = request.getSenderFirstName();
        String senderLast = request.getSenderLastName();
        String senderEmail = request.getSenderEmail();

        String recipientFirst = request.getRecipientFirstName();
        String recipientLast = request.getRecipientLastName();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        String strTime = timeFormat.format(date);

        String subject = "[Pete’s Parking] A Parking Pal Request you sent has been Accepted!";
        String body = "Dear " + senderFirst + " " + senderLast + ",\n" +
                "\n" +
                "Your parking pal request on Pete’s Parking has been accepted. Please read the following information about the request:\n" +
                "\n" +
                "Date Request Accepted: " + strDate + "\n" +
                "\n" +
                "Time Request Accepted: " + strTime + "\n" +
                "\n" +
                "User who Accepted Request: " + recipientFirst + " " + recipientLast + "\n" +
                "\n" +
                "To see more about your new friend, you can log into Pete’s Parking and navigate to the Parking Pals section.\n" +
                "\n" +
                "Pete’s Parking Team" + "\n" +
                "petesparkingpurdue@gmail.com";
        sendEmail(senderEmail, subject, body);
    }

    public void createParkingPalRejectedEmail(FriendDTO request) throws NoSuchAlgorithmException, KeyManagementException {
        String senderFirst = request.getSenderFirstName();
        String senderLast = request.getSenderLastName();
        String senderEmail = request.getSenderEmail();

        String recipientFirst = request.getRecipientFirstName();
        String recipientLast = request.getRecipientLastName();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        String strTime = timeFormat.format(date);

        String subject = "[Pete’s Parking] A Parking Pal Request you sent has been Rejected!";
        String body = "Dear " + senderFirst + " " + senderLast + ",\n" +
                "\n" +
                "Your parking pal request on Pete’s Parking has been rejected. Please read the following information about the request:\n" +
                "\n" +
                "Date Request Rejected: " + strDate + "\n" +
                "\n" +
                "Time Request Rejected: " + strTime + "\n" +
                "\n" +
                "User who Rejected Request: " + recipientFirst + " " + recipientLast + "\n" +
                "\n" +
                "To see more, you can log into Pete’s Parking and navigate to the Parking Pals section.\n" +
                "\n" +
                "Pete’s Parking Team" + "\n" +
                "petesparkingpurdue@gmail.com";
        sendEmail(senderEmail, subject, body);
    }

    public void createPoorParkReportedEmail(PoorParkReportDTO poorParkReport, UserDTO user) throws NoSuchAlgorithmException, KeyManagementException {
        String recipientEmail = user.getEmail();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        String strTime = timeFormat.format(date);

        String subject = "[Pete’s Parking] You have been reported by another user!";
        String body = "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n" +
                "\n" +
                "A vehicle linked to your Pete’s Parking account has been reported by another user. Please read the following information about the report:\n" +
                "\n" +
                "Date of Report: " + strDate + "\n" +
                "\n" +
                "Time of Report: " + strTime + "\n" +
                "\n" +
                "License Plate: " + poorParkReport.getLicensePlate() + "\n" +
                "\n" +
                "Parking Lot: " + poorParkReport.getParkingLot() + "\n" +
                "\n" +
                "Description: " + poorParkReport.getDescription() + "\n" +
                "\n" +
                "If you are found in violation of parking rules, you may receive a parking ticket. A Pete’s Parking admin will review this report shortly. Thank you for understanding.\n" +
                "\n" +
                "Pete’s Parking Team" + "\n" +
                "petesparkingpurdue@gmail.com";
        sendEmail(recipientEmail, subject, body);
    }

    public void createExpReportedEmail(ExpReportDTO expReport, UserDTO user) throws NoSuchAlgorithmException, KeyManagementException {
        String recipientEmail = user.getEmail();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        String strTime = timeFormat.format(date);

        String subject = "[Pete’s Parking] You have been reported by another user!";
        String body = "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n" +
                "\n" +
                "A vehicle linked to your Pete’s Parking account has been reported by another user. Please read the following information about the report:\n" +
                "\n" +
                "Date of Report: " + strDate + "\n" +
                "\n" +
                "Time of Report: " + strTime + "\n" +
                "\n" +
                "License Plate: " + expReport.getLicensePlate() + "\n" +
                "\n" +
                "Parking Lot: " + expReport.getParkingLot() + "\n" +
                "\n" +
                "Description: " + expReport.getDescription() + "\n" +
                "\n" +
                "If you are found in violation of parking rules, you may receive a parking ticket. A Pete’s Parking admin will review this report shortly. Thank you for understanding.\n" +
                "\n" +
                "Pete’s Parking Team" + "\n" +
                "petesparkingpurdue@gmail.com";
        sendEmail(recipientEmail, subject, body);
    }

    public void createExpiringTimerEmail(BookingDTO booking, long timer) throws NoSuchAlgorithmException, KeyManagementException {
        String firstName = booking.getFirstName();
        String lastName = booking.getLastName();
        String recipientEmail = booking.getEmail();

        String toTime = booking.getToTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm aa");
        String outputTime = toTime;
        try {
            Date date = inputFormat.parse(toTime);
            outputTime = outputFormat.format(date);
            System.out.println(outputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String subject = "[Pete’s Parking] Your Timer is Almost Expired!";
        String body = "Dear " + firstName + " " + lastName + ",\n" +
                "\n" +
                "Your Pete’s Parking booking is almost expired. Please read the following information about your reservation to avoid being late:\n" +
                "\n" +
                "Parking Lot: " + booking.getParkingName() + "\n" +
                "\n" +
                "Expiration Time: " + outputTime + "\n" +
                "\n" +
                "Time Remaining: " + timer + " minutes\n" +
                "\n" +
                "If you are still parked after your timer has expired, you may receive a parking ticket. Please ensure that your car is removed before the reservation ends. Thank you.\n" +
                "\n" +
                "Pete’s Parking Team" + "\n" +
                "petesparkingpurdue@gmail.com";
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
            message.setFrom(new InternetAddress("petesparkingpurdue@gmail.com"));

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
