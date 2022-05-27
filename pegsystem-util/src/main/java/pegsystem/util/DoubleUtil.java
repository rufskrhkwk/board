package pegsystem.util;

public class DoubleUtil {

	/**
	 * obj가 null이면 0, 아니면 return
	 * @param obj
	 * @return
	 */
	public static double isNull(Object obj) {
		String temp = StringUtil.isNull(obj);
		return "".equals(temp) ? 0 : Double.parseDouble(temp);
	}
}
