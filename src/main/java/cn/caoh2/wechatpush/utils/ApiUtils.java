package cn.caoh2.wechatpush.utils;

import cn.caoh2.wechatpush.config.WechatConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Component
public class ApiUtils {

    @Resource
    private WechatConfig wechatConfig;

    /**
     * 彩虹屁
     */
    public String getCaiHongPi() {
        String httpUrl = "http://api.tianapi.com/caihongpi/index?key=" + wechatConfig.API_AK;
        JSONArray newsList = getNewsList(httpUrl);
        return newsList.getJSONObject(0).getString("content");
    }

    /**
     * 英语每日一句
     */
    public Map<String, String> getEnsentence() {
        String httpUrl = "http://api.tianapi.com/ensentence/index?key=" + wechatConfig.API_AK;
        JSONArray newsList = getNewsList(httpUrl);
        String en = newsList.getJSONObject(0).getString("en");
        String zh = newsList.getJSONObject(0).getString("zh");
        Map<String, String> map = new HashMap<>();
        map.put("zh", zh);
        map.put("en", en);
        return map;
    }

    /**
     * 神回复*
     */
    public Map<String, String> getGodreply() {
        String httpUrl = "http://api.tianapi.com/godreply/index?key=" + wechatConfig.API_AK;
        JSONArray newsList = getNewsList(httpUrl);
        String title = newsList.getJSONObject(0).getString("title");
        String content = newsList.getJSONObject(0).getString("content");
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        return map;
    }

    private static JSONArray getNewsList(String httpUrl) {
        BufferedReader reader;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        return Objects.requireNonNull(jsonObject).getJSONArray("newslist");
    }
}
