package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class HomePageController {
    public TextField txtToEmail;
    public TextField txtFromEmail;
    public TextField txtSubject;
    public TextField txtMassage;
    public AnchorPane context;

    public void sendingEmail(ActionEvent actionEvent) {
        String ToEmail = txtToEmail.getText();
        String FromEmail = txtFromEmail.getText();
        String FromEmailPassword = "Your mail password";
        String Subjects = txtSubject.getText();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FromEmail, FromEmailPassword);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
            message.setSubject(Subjects);
            message.setText(txtMassage.getText());

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
