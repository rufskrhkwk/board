package pegsystem.util;

public class LongUtil {

	/**
	 * obj가 null이면 0, 아니면 return
	 * @param obj
	 * @return
	 */
	public static long isNull(Object obj) {
		String temp = StringUtil.isNull(obj);
		return "".equals(temp) ? 0 : Long.parseLong(temp);
	}
}
