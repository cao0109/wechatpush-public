package cn.caoh2.wechatpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WechatPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatPushApplication.class, args);
    }

}
