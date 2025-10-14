package cn.iocoder.yudao.framework.common.util.spring;

public class SetProfileByEnv {
    public static void setProfile(){
        System.setProperty("spring.profiles.active", "dev");
    }
}
