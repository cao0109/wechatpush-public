package cn.caoh2.wechatpush.web;

import cn.caoh2.wechatpush.config.WechatConfig;
import cn.caoh2.wechatpush.entity.Weather;
import cn.caoh2.wechatpush.utils.ApiUtils;
import cn.caoh2.wechatpush.utils.JiNianRiUtils;
import cn.caoh2.wechatpush.utils.WeatherUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class Pusher {

    @Resource
    private WechatConfig wechatConfig;
    @Resource
    private WeatherUtils weatherUtils;

    @Resource
    private ApiUtils apiUtils;

    @Scheduled(cron = "0/20 * * * * ?")
    public void push() {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(wechatConfig.APP_ID);
        wxStorage.setSecret(wechatConfig.APP_SECRET);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(wechatConfig.USER_ID.get(0))
                .templateId(wechatConfig.TEMPLATE_ID.get(0))
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        Weather weather = weatherUtils.getWeather();
        Map<String, String> map = apiUtils.getEnsentence();
        Map<String, String> godreply = apiUtils.getGodreply();

        templateMessage.addData(new WxMpTemplateData("date", weather.getDate() + "  " + weather.getWeek(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("weather", weather.getText_now(), "#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low-temp", weather.getLow() + "", "#173177"));
        templateMessage.addData(new WxMpTemplateData("temp", weather.getTemp() + "", "#EE212D"));
        templateMessage.addData(new WxMpTemplateData("high-temp", weather.getHigh() + "", "#FF6347"));
        templateMessage.addData(new WxMpTemplateData("wind_class", weather.getWind_class() + "", "#42B857"));
        templateMessage.addData(new WxMpTemplateData("wind_dir", weather.getWind_dir() + "", "#B95EA3"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", apiUtils.getCaiHongPi(), "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRiUtils.getLianAi() + "", "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri1", JiNianRiUtils.getBirthday_Jo() + "", "#FFA500"));
        templateMessage.addData(new WxMpTemplateData("shengri2", JiNianRiUtils.getBirthday_Hui() + "", "#FFA500"));
        templateMessage.addData(new WxMpTemplateData("en", map.get("en") + "", "#C71585"));
        templateMessage.addData(new WxMpTemplateData("zh", map.get("zh") + "", "#C71585"));
        templateMessage.addData(new WxMpTemplateData("title", godreply.get("title") + "", "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("content", godreply.get("content") + "", "#FF1493"));

/*        String beizhu = "xxx";

        if(JiNianRiUtils.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }
        if(JiNianRiUtils.getBirthday_Jo()  == 0){
            beizhu = "今天是xxx生日，生日快乐呀！";
        }
        if(JiNianRiUtils.getBirthday_Hui()  == 0){
            beizhu = "今天是xxx生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));*/

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
