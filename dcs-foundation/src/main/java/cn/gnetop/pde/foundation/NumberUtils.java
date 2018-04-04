package cn.gnetop.pde.foundation;

import java.util.Random;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

	private static Random random = new Random();

	public static int rand(int range) {
		return random.nextInt(range);
	}

	public static boolean isPrettyNum(long num) {
		String pattern = "(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3,}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){3,})\\d";
		return String.valueOf(num).matches(pattern);
	}
	
	public static Long toLong(Object v){
		try {
			Long l = Long.parseLong(String.valueOf(v));
			return l;
		} catch (NumberFormatException e) {
			throw e;
		}
	}
	
	public static void main(String[] args) {
		Double d = 16806116D;
		System.out.println(d.longValue());
	}

}
