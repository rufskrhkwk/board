package pegsystem.util;

public class IntegerUtil {

	/**
	 * obj가 null이면 0, 아니면 return
	 * @param obj
	 * @return
	 */
	public static int isNull(Object obj) {
		String temp = StringUtil.isNull(obj);
		return "".equals(temp) ? 0 : Integer.parseInt(temp);
	}
}
