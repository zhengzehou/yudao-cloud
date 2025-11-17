package cn.iocoder.yudao.module.im.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BirthdayUtils {

    /**
     * 计算距离下一个生日的天数
//     * @param birthDate 生日日期字符串 (格式: "yyyy-MM-dd" 或 "MM-dd")
     * @return 距离下一个生日的天数
     */
    public static long getDaysUntilNextBirthday(LocalDate birthday) {
        if(birthday == null){
            return -1;
        }
        try {
            LocalDate today = LocalDate.now();
//            LocalDate birthday = parseBirthDate(birthDate, today.getYear());

            LocalDate nextBirthday = birthday;
            if (today.isAfter(birthday)) {
                nextBirthday = birthday.plusYears(1);
            }

            return ChronoUnit.DAYS.between(today, nextBirthday);
        } catch (Exception e) {
            throw new IllegalArgumentException("无效的生日日期格式: " + birthday, e);
        }
    }

    /**
     * 解析生日日期
     */
    private static LocalDate parseBirthDate(String birthDate, int currentYear) {
        if (birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // 格式: yyyy-MM-dd
            return LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } else if (birthDate.matches("\\d{2}-\\d{2}")) {
            // 格式: MM-dd
            String fullDate = currentYear + "-" + birthDate;
            return LocalDate.parse(fullDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            throw new IllegalArgumentException("不支持的日期格式");
        }
    }

//    /**
//     * 获取生日相关信息
//     * @param birthDate 生日日期
//     * @return 包含天数和其他信息的字符串
//     */
//    public static String getBirthdayInfo(String birthDate) {
//        long days = getDaysUntilNextBirthday(birthDate);
//
//        if (days == 0) {
//            return "🎉 今天是你的生日！生日快乐！";
//        } else if (days == 1) {
//            return "明天就是你的生日啦！";
//        } else {
//            return "距离你的下一个生日还有 " + days + " 天";
//        }
//    }
}