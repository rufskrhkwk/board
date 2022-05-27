package pegsystem.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;


/**
 * @Class Name	: DateUtil.java
 * @Description : 날짜관련 Util Class
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.01.31
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.01.31	  박진우   		최초생성
 */
public class DateUtil {

	
	/**
	 * 현재 시간을 리턴
	 * @return 현재시간(String)
	 */
	public static String getCurrentTime() {
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String retVal = dayTime.format(new Date(time));
		
		return retVal;
	}
	
	
	/**
	 * 현재 년월일을 구해서 리턴(20170208)
	 * @return 현재날짜(String)
	 */
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		
		String year = String.valueOf(y);
		String month = String.valueOf(m);
		String date = String.valueOf(d);
		
		if(m < 10) month = "0" + String.valueOf(m);
		if(d < 10) date = "0" + String.valueOf(d);
		
		return year + month + date;
	}
	
	
	/**
	 * 만 나이 계산
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @param kind
	 * @return 만 나이(Integer)
	 */
	public static int getAge(int birthYear, int birthMonth, int birthDay) {
		Calendar cal = Calendar.getInstance();
		int currentYear  = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		int currentDay   = cal.get(Calendar.DAY_OF_MONTH);
		int age = currentYear - birthYear;
		if(((birthMonth * 100) + birthDay) > ((currentMonth * 100) + currentDay)) age--;		// 생일 안 지난 경우 -1
		return age;
	}
	
	
	/**
	 * 연말정산용 만 나이 계산(정산년도 말일을 기준으로 계산)
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @param kind
	 * @return 만 나이(Integer)
	 */
	public static int getAge(String psnno, String sac_yy) {
		String front = psnno.substring(0, 6);
		String no7 = psnno.substring(6, 7);
		String birth = "";
		if("3".equals(no7) || "4".equals(no7) || "7".equals(no7) || "8".equals(no7)) birth = "20" + front;
		else birth = "19" + front;
		int year = Integer.parseInt(birth.substring(0, 4));
		int month = Integer.parseInt(birth.substring(4, 6));
		int day = Integer.parseInt(birth.substring(6, 8));
		
		int currentYear  = Integer.parseInt(sac_yy);
		int currentMonth = 12;
		int currentDay   = 31;
		int age = currentYear - year;
		if(((month * 100) + day) > ((currentMonth * 100) + currentDay)) age--;		// 생일 안 지난 경우 -1
		return age;
	}
	
	
	/**
	 * 현재 월에서 param만큼 더해서 리턴
	 * @param param
	 * @return 계산된 날짜(String)
	 */
	public static String getCustomMonthDate(int param) {
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.MONTH, param);
		Date monthago = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		
		return sdf.format(monthago);
	}
	

	/**
	 * date를 년월일로 구분해서 리턴
	 * @param date
	 * @param divide
	 * @return
	 */
	public static String setDatePattern(String date, String divide) {
		return date.substring(0, 4) + divide + date.substring(4, 6) + divide + date.substring(6, 8);
	}
	
	
	/**
	 * 두 날짜 차이를 계산
	 * @param str
	 * @param end
	 * @return
	 */
	public static long getDateCalculate(String str, String end) {
		long result = 0;
        String strFormat = "yyyyMMdd";
        
        // SimpleDateFormat 을 이용해서 startDate와 endDate의 Date 객체를 생성한다.
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        try{
            Date startDate = sdf.parse(str);
            Date endDate = sdf.parse(end);
 
            // 두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            result = (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        
        return result;
	}
	
	
	/**
	 * 2011.08.09
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 *
	 * @param
	 * @return Timestamp 값
	 * @exception MyException
	 * @see
	 */
	public static String getSysdate() {
		String result = null;
		String pattern = "yyyyMMddHHmmss";
		
		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			result = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
    /**
     * 2011.08.09
     * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {
    	String result = null;
    	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
    	String pattern = "yyyyMMddHHmmssSSS";

    	try {
    		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
    		Timestamp ts = new Timestamp(System.currentTimeMillis());
    		result = sdfCurrent.format(ts.getTime());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
	
}
