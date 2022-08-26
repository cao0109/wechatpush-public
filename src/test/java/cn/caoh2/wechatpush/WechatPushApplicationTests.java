package cn.caoh2.wechatpush;

import cn.caoh2.wechatpush.config.WechatConfig;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@SpringBootTest
class WechatPushApplicationTests {

    @Resource
    private WechatConfig wechatConfig;

    @Autowired
    private Environment env;

    @Test
    void contextLoads() {

    }

}
