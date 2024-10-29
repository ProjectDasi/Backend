package potato.dasi.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MultiDateDeserializer extends JsonDeserializer<Date> {
	private static final List<SimpleDateFormat> dateFormats = List.of(
	        new SimpleDateFormat("yyyy-MM-dd"),
	        new SimpleDateFormat("yyyy.MM"),
	        new SimpleDateFormat("yyyy.MM.dd")
	    );

	    @Override
	    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
	        String date = p.getText();
	        
	        // 빈 문자열이면 null 반환
	        if (date == null || date.trim().isEmpty()) {
	            return null;
	        }
	        
	        for (SimpleDateFormat dateFormat : dateFormats) {
	            try {
	                return dateFormat.parse(date);
	            } catch (ParseException e) {
	                // Ignore and try the next format
	            }
	        }
	        throw new RuntimeException("Unparseable date: " + date);
	    }
}
