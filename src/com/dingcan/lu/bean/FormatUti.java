package com.dingcan.lu.bean;

import java.text.DecimalFormat;

public class FormatUti {
	public static String formatNumber(double d) {
		final DecimalFormat decimalFormat = new DecimalFormat("#.0");
		return decimalFormat.format(d);
	}
}
