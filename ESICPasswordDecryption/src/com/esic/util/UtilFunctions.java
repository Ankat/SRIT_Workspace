package com.esic.util;

public class UtilFunctions {

	public static String checkNull(String inData) {
		if (inData == null) {
			return "";
		} else {
			if (inData.equalsIgnoreCase("null") || inData.equals(" ")) {
				inData = "";
			}
			return inData;
		}
	}
}
