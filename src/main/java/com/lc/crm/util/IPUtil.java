package com.lc.crm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IPUtil {
	/**
	 * 把一个long类型的数字转换为字符串形式的IPv4地址。
	 *
	 * @param ipl
	 *            long类型的IP地址对应的数字
	 * @return 字符串类型的IPv4形式的IP地址
	 */
	private static String long2ip(Long ipl) {
		Long a = (ipl & 4278190080L) >> 24;
		Long b = (ipl & 16711680L) >> 16;
		Long c = (ipl & 65280L) >> 8;
		Long d = ipl & 255L;

		return (String.valueOf(a).concat(".").concat(String.valueOf(b)).concat(".").concat(String.valueOf(c))
				.concat(".").concat(String.valueOf(d)));
	}

	/**
	 * 把一个字符串形式的IPv4的IP转换为long类型的数字
	 *
	 * @param ip
	 *            字符串类型的IPv4的通常格式的IPv4地址
	 * @return IP对应的long类型的数值
	 */
	public static Long ip2long(String ip) {
		String[] ips = ip.split("\\.");

		if (4 == ips.length) {
			return ((Long.parseLong(ips[0].trim()) << 24) | (Long.parseLong(ips[1].trim()) << 16)
					| (Long.parseLong(ips[2].trim()) << 8) | Long.parseLong(ips[3].trim()));
		} else {
			return -1L;
		}
	}
	
//	@Test
//	public void testip(){
//		System.out.println(ip2long("183.56.128.187"));
//	}

	/**
	 * 判断一个IP是否在一组IP段之中。
	 *
	 * @param ip
	 *            待判断的字符串类型的单个IPv4
	 * @param ranges
	 *            一组IP段，每个IP段之间用英文逗号分割，每个IP段的两个IP之间用-分割
	 * @return 指定IP只要在任意IP段之间即返回true，否则返回false
	 */
	public static Boolean ipInRange(String ip, String ranges) {
		if (-1 == ranges.indexOf("-"))
			return false;

		String[] range = ranges.split(",");
		Long ipn = ip2long(ip);
		for (String rangeIP : range) {
			String[] range12 = rangeIP.split("-");
			if (2 == range12.length) {
				Long range1 = ip2long(range12[0]);
				Long range2 = ip2long(range12[1]);

				Boolean in = ipn >= range1 && ipn <= range2;
				if (in)
					return true;
			}
		}

		return false;
	}

	public static boolean isIP(String addr) {
		if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
			return false;
		}
		/**
		 * 判断IP格式和范围
		 */
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

		Pattern pat = Pattern.compile(rexp);

		Matcher mat = pat.matcher(addr);

		boolean ipAddress = mat.find();

		return ipAddress;
	}
}
