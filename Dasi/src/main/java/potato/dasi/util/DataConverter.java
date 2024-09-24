package potato.dasi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {
    public static String convertDate(Date date) {
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
}
