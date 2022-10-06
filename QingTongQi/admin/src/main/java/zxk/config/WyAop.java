package zxk.config;

import com.alibaba.fastjson.JSONObject;
import zxk.common.WyEmail;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
@Aspect
public class WyAop {
    @Resource
    RabbitTemplate rabbitTemplate;
    @AfterReturning(value = "execution(public * zxk.service.impl.UmsUserServiceImpl.add(..))")
    public void afterUserAdd(JoinPoint joinPoint) throws Throwable {
        // 取出参数列表
        Object[] args = joinPoint.getArgs();
        String phone = args[1].toString();
        String email = args[2].toString();
        String password = args[3].toString();
        WyEmail wyEmail = new WyEmail(
                email
                ,"尊敬的青铜器爱好者"
                ,"系统为您创建了用户,登录名:" + phone + ", 密码: " + password
        );
        rabbitTemplate.convertAndSend("email", JSONObject.toJSONString(wyEmail));
    }
}
