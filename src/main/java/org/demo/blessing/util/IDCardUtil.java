package org.demo.blessing.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class IDCardUtil {
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(IDCardUtil.class.getClassLoader().getResourceAsStream("develop/IDCardAddressCode.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 校验规则： 
     * 1、将前面的身份证号码17位数分别乘以不同的系数。第i位对应的数为[2^(18-i)]mod11。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2、将这17位数字和系数相乘的结果相加
     * 3、用加出来和除以11，看余数是多少？
     * 4、余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2
     * @return 返回false说明，身份证号码不符合规则，返回true说明身份证号码符合规则 
     */
	public static boolean checkIdCard(String idCard) {
		boolean flag = false;  
        int len = idCard.length();  
        int kx = 0;  
        for(int i=0;i<len-1;i++) {  
            int x = Integer.parseInt(String.valueOf(idCard.charAt(i)));  
            int k = 1;  
            for(int j=1;j<18-i;j++) {  
                k *= 2;  
            }  
            kx += k*x;  
        }  
        int mod = kx%11;  
        int[] mods = {0,1,2,3,4,5,6,7,8,9,10};  
        Character[] checkMods = {'1','0','X','9','8','7','6','5','4','3','2'};  
        for(int i=0;i<11;i++){  
            if(mod==mods[i]){  
                Character lastCode = idCard.charAt(len-1);  
                if(checkMods[i].equals(lastCode)){  
                    flag = true;  
                }  
            }  
        }  
        return flag;
	}
	
	public static String parseSex(String idCard) {
		String sex = null;
		char c = idCard.charAt(idCard.length() - 2);
		int gender = Integer.parseInt(String.valueOf(c));
		if(gender % 2 == 0) {
			sex = "女";
		} else {
			sex = "男";
		}
		return sex;
	}
	
	public static int parseAge(String idCard) {
		int age = 0;
		String birthDayStr = idCard.substring(6, 14);
		Date birthDay = null;
		try {
			birthDay = new SimpleDateFormat("yyyyMMdd").parse(birthDayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		if(calendar.before(birthDay)) {
			throw new IllegalArgumentException("您还没有出生");
		}
		int yearNow = calendar.get(Calendar.YEAR);  
        int monthNow = calendar.get(Calendar.MONTH)+1;  
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);  
        calendar.setTime(birthDay);  
        int yearBirth = calendar.get(Calendar.YEAR);  
        int monthBirth = calendar.get(Calendar.MONTH)+1;  
        int dayBirth = calendar.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;
        if(monthNow<=monthBirth){
            if(monthNow==monthBirth&&dayNow<dayBirth){
                age--;
            }
        }else{
            age--;  
        }
		return age;
	}
	
	public static String parseAddress(String idCard) {
		String address = null;
		String addressCode = idCard.substring(0, 6);
		address = new String(properties.getProperty(addressCode));
		return address;
	}
	
}
