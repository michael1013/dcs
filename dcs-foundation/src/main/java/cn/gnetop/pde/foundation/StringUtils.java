package cn.gnetop.pde.foundation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 只要有为空的字符串，即返回false("与"关系判断)
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isNotBlank(String... strs) {
		if (ArrayUtils.isNotEmpty(strs)) {
			for (String str : strs) {
				if (isBlank(str)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 只有有不为空的字符串，即返回true("或"关系判断)
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean hasNotBlank(String... strs) {
		if (ArrayUtils.isNotEmpty(strs)) {
			for (String str : strs) {
				if (isNotBlank(str)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean hasBlank(String... strs) {
		if (ArrayUtils.isNotEmpty(strs)) {
			for (String s : strs) {
				if (isBlank(s)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public static boolean isAllBlank(String... strs) {
		return !hasNotBlank(strs);
	}

	public static String nvl(String str) {
		if (null == str) {
			return "";
		} else {
			return str;
		}
	}

	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = Pattern.compile("_(\\w)").matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String humpToLine(String str) {
		Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(lineToHump("ajsdf_jaks_iefs"));
		System.out.println(humpToLine("lajsdHALahldfAHKLASDF"));
	}

}
