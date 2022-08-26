package cn.caoh2.wechatpush.utils;

import cn.caoh2.wechatpush.config.WechatConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 纪念日工具类
 *
 * @author caoh2
 * @version 2.0
 */
@Component
public class AnniversaryUtils {

    @Resource
    private WechatConfig wechatConfig;

    public int getLianAi() {
        return calculationLianAi(wechatConfig.ANNIVERSARY);
    }

    public int getBirthday_Boy() {
        try {
            return calculationBirthday(wechatConfig.BOY_BIRTHDAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getBirthday_Girl() {
        try {
            return calculationBirthday(wechatConfig.GIRL_BIRTHDAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int calculationBirthday(String clidate) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果
        if (days == 0) {
            return 0;
        } else {
            return days;
        }
    }

    public int calculationLianAi(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }
}
