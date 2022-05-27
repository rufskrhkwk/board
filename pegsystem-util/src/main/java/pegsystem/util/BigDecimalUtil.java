package pegsystem.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
	
	
	
	public static BigDecimal isNull(Object obj) {
		return obj == null ? new BigDecimal(0) : (BigDecimal) obj;
	}
	
	
	
	
}
