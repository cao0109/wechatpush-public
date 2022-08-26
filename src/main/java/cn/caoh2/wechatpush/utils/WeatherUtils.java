package cn.caoh2.wechatpush.utils;

import cn.caoh2.wechatpush.config.WechatConfig;
import cn.caoh2.wechatpush.entity.Weather;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WeatherUtils {

    @Resource
    private WechatConfig wechatConfig;

    public Weather getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("district_id", wechatConfig.CITY_CODE.get(0)); // 行政代码
        map.put("data_type", "all");//这个是数据类型
        map.put("ak", wechatConfig.BAIDU_AK);
        String res = restTemplate.getForObject("https://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type={data_type}&ak={ak}", String.class, map);
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        Weather weather = weathers.get(0);
        weather.setText_now(now.getString("text"));
        weather.setTemp(now.getString("temp"));
        weather.setWind_class(now.getString("wind_class"));
        weather.setWind_dir(now.getString("wind_dir"));
        return weather;
    }
}
