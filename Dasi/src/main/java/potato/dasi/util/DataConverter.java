package potato.dasi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataConverter {
    public static String convertDate(Date date) {
    	if(date == null)
    		return null;
    	
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        switch (dateString) {
            case "2999-12-31":
                return "상시채용";
            case "9999-01-01":
                return "채용시까지";
            default:
                return dateString; // 시간이 없는 날짜 문자열로 반환
        }
    }
    
    public static Date convertStringToDate(String dateString) {
    	if (dateString == null || dateString.trim().isEmpty()) {
            return null; // 빈 문자열이나 null인 경우 null 반환
        }
    	
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 형식 지정
        try {
            // 문자열을 Date로 변환
            return formatter.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 변환 실패 시 null 반환
        }
    }
    
    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null; 
        }

        // 모바일 번호 처리 (10~11자리)
        if (phoneNumber.matches("\\d{10,11}")) {
            if (phoneNumber.length() == 11) {
                return phoneNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
            } else if (phoneNumber.length() == 10) {
                return phoneNumber.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "$1-$2-$3");
            }
        }

        // 유선전화번호 처리 (지역번호 포함)
        if (phoneNumber.matches("^0\\d{1,2}\\d{3,4}\\d{4}$")) {
            if (phoneNumber.length() == 10) {
                return phoneNumber.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
            } else if (phoneNumber.length() == 9) {
                return phoneNumber.replaceAll("(\\d{2})(\\d{3})(\\d{4})", "$1-$2-$3");
            }
        }

        // 다른 경우는 그대로 반환
        return phoneNumber;
    }
    
    // 정규식 패턴 설정
    private static final String PHONE_NUMBER_PATTERN = 
            "\\b(01[016789]-\\d{3,4}-\\d{4})\\b|\\b(\\d{2,3}-\\d{3,4}-\\d{4})\\b";

    public static boolean containsPhoneNumber(String input) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    
    public static String convertToOther(String workInfo, String replaceWord) {
    	if(workInfo == null || workInfo.isEmpty() || workInfo.equals("None")) {
    		return replaceWord;
    	}
   
    	return workInfo;
    }
}
