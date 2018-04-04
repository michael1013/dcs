package cn.gnetop.pde.foundation;

import java.security.InvalidAlgorithmParameterException;
import java.util.Random;

import cn.gnetop.pde.foundation.encryte.DESBaseUtils;

public class EncrypteUtils {

	private static final String[] pwdArrays = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "q", "w", "e", "r", "t", "y",
			"u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "Q",
			"W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C",
			"V", "B", "N", "M" };

	private static final String[] pwdLetterArrays = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d",
			"f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "Q", "W", "E", "R", "T", "Y", "U", "I",
			"O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M" };

	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	private static Random random = new Random();

	public static String randomPwd(int length) {
		StringBuffer sb = new StringBuffer();
		// 密码第一位为字母
		sb.append(pwdArrays[random.nextInt(pwdLetterArrays.length)]);
		for (int i = 0; i < length - 1; i++) {
			sb.append(pwdArrays[random.nextInt(pwdArrays.length)]);
		}
		return sb.toString();
	}

	/**
	 * DES算法，加密
	 *
	 * @param data
	 *            待加密字符串
	 * @param key
	 *            加密私钥，长度不能够小于8位
	 * @return 加密后的字节数组，一般结合Base64编码使用
	 * @throws InvalidAlgorithmParameterException
	 * @throws Exception
	 */
	public static String desEncode(String key, String data) {
		try {
			return DESBaseUtils.encrypt(data, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * DES算法，解密
	 *
	 * @param data
	 *            待解密字符串
	 * @param key
	 *            解密私钥，长度不能够小于8位
	 * @return 解密后的字节数组
	 * @throws Exception
	 *             异常
	 */
	public static String desDecode(String key, String data) {
		try {
			return DESBaseUtils.decrypt(data, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) {
		String data = "1.0|mnqy|20170526164500";
		System.out.println(System.currentTimeMillis());
		System.out.println(desEncode("gNetopSZ", data));
		System.out.println(desDecode("gNetopSZ", "fK/cDNiz+ynp7JMTxMzI2TNwUnub2deQ"));
	}
}
