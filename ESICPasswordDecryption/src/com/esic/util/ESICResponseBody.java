package com.esic.util;

import java.util.List;
import com.esic.decrypt.ESICDeCrypt;
import com.esic.model.Userdetails;

public class ESICResponseBody {

	public static String constructTable(List<Userdetails> lstUserdetails, ESICDeCrypt esicDeCrypt) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1' class='listTable'>");
		sb.append("<thead>");
		sb.append("<tr>");
		sb.append("<th>Username</th>");
		sb.append("<th>Password</th>");
		sb.append("<th>Created Date</th>");
		sb.append("<th>Updated Date</th>");
		sb.append("<th>Password Pervious 1</th>");
		sb.append("<th>Password Pervious 2</th>");
		sb.append("<th>Password Pervious 3</th>");
		sb.append("</tr>");
		sb.append("</thead>");
		
		sb.append("</tbody>");
		for (Userdetails userdetails : lstUserdetails) {
			sb.append("<tr>");
			sb.append("<td>" + userdetails.getUsername() + "</td>");
			sb.append("<td>" + esicDeCrypt.getDecryptData(userdetails.getPassword().trim()) + "</td>");
			sb.append("<td>" + userdetails.getCreatedDate() + "</td>");
			sb.append("<td>" + userdetails.getUpdatedDate() + "</td>");
			sb.append("<td>" + esicDeCrypt.getDecryptData(userdetails.getPASSWORD_PREVIOUS_1().trim()) + "</td>");
			sb.append("<td>" + esicDeCrypt.getDecryptData(userdetails.getPASSWORD_PREVIOUS_2().trim()) + "</td>");
			sb.append("<td>" + esicDeCrypt.getDecryptData(userdetails.getPASSWORD_PREVIOUS_3().trim()) + "</td>");
			sb.append("</tr>");
		}
		sb.append("</tbody>");

		sb.append("</table>");
		return sb.toString();
	}
}
