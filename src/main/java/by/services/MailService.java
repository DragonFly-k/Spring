package by.services;

import by.model.AccountScooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class MailService {
    @Autowired
    JavaMailSender emailSender;

    @Async("asyncExecutor")
    public void sendMailAboutRent(AccountScooter accountScooter) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMsg = "<h1>Thank you!</h1>";
        Date date = new Date();
        long diff = date.getTime() - accountScooter.getRentTime().getTime();
        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        htmlMsg += "<p>Minutes: "+diffMinutes+"</p>";
        htmlMsg += "<h3>Total Price: "+(diffMinutes*accountScooter.getScooter().getPrice()+1)+"$</h3>";
        htmlMsg += "</br>";
        message.setContent(htmlMsg, "text/html");
        helper.setTo(accountScooter.getAccount().getEmail());
        helper.setSubject("KickSharing");
        this.emailSender.send(message);
    }
}