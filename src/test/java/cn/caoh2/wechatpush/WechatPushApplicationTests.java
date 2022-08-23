package cn.caoh2.wechatpush;

import cn.caoh2.wechatpush.config.WechatConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class WechatPushApplicationTests {

    @Resource
    private WechatConfig wechatConfig;

    @Test
    void contextLoads() {

        String appSecret = wechatConfig.APP_SECRET;
        System.out.println(appSecret);

        List<String> user_id = wechatConfig.USER_ID;
        for (String s : user_id) {
            System.out.println(s);
        }

    }

}
