package zxk.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Component
public class EmailListener {
    @Resource
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String from;
    @RabbitListener(queues = "email")
    public void handler(Message message) {
        try {
            String content = new String(message.getBody(), StandardCharsets.UTF_8);
            WyEmail wyEmail = JSONObject.parseObject(content, WyEmail.class);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(wyEmail.getTo());
            mailMessage.setText(wyEmail.getText());
            mailMessage.setSubject(wyEmail.getSubject());
            mailMessage.setFrom(from);
            javaMailSender.send(mailMessage);
        } catch (Exception ex) {
            System.out.println(message);
            ex.printStackTrace();
        }
    }
}
