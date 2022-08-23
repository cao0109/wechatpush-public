package cn.caoh2.wechatpush.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Configuration
@PropertySource(value = {"classpath:wechat.properties"})
public class WechatConfig {

    @Value("${appID}")
    public String APP_ID;
    @Value("${appSecret}")
    public String APP_SECRET;

    @Value("${userID}")
    public List<String> USER_ID;

    @Value("${templateID}")
    public List<String> TEMPLATE_ID;

    @Value("${baiduAK}")
    public String BAIDU_AK;

    @Value("${cityCode}")
    public String CITY_CODE;

    @Value("${apiKey}")
    public String API_AK;


}
