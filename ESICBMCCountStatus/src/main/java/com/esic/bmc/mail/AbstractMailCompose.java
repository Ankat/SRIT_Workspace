package com.esic.bmc.mail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.model.ReportType;
import com.esic.bmc.util.Resource;

public abstract class AbstractMailCompose {

	@Autowired
	private Resource resource;
	private File esicCountReportFile = null;
	private Properties props = null;
	private StringBuilder body = null;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	protected Session getSession() {
		return Session.getInstance(getProperty(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(resource.getProperty("mail.username"), resource.getProperty("mail.password"));
			}
		});
	}

	public void setProperty(Properties props) {
		this.props = props;
	}

	private Properties getProperty() {
		this.props = new Properties();
		this.props.put("mail.smtp.auth", resource.getProperty("mail.smtp.auth"));
		this.props.put("mail.smtp.starttls.enable", resource.getProperty("mail.smtp.starttls.enable"));
		this.props.put("mail.smtp.host", resource.getProperty("mail.smtp.host"));
		this.props.put("mail.smtp.port", resource.getProperty("mail.smtp.port"));
		return this.props;
	}

	public void setAttachmentFile(File esicCountReportFile) {
		this.esicCountReportFile = esicCountReportFile;
	}

	public File getAttachmentFile() {
		return esicCountReportFile;
	}

	public String getEmailSubject(ReportType reportType) {
		Date date = new Date();
		return "ERP-L2 " + StringUtils.capitalize(reportType.toString().toLowerCase()) + " Count " + dateFormat.format(date);
	}

	public void setMessageBody(List<ESICIncident> esicIncidents, ReportType reportType) {
		body = new StringBuilder();
		Date date = new Date();
		body.append("<p style='font-size:1.0em'>Hi All,</p>");
		body.append("<p style='font-size:1.0em'>Please find below the report for the " + StringUtils.capitalize(reportType.toString().toLowerCase()) + " count of ERP L2 tickets for " + dateFormat.format(date) + ".</p>");
		body.append("<table border='0' cellspacing='0' style='border-collapse:collapse;width:400px;font-size:0.9em'>");
		body.append("<tr style='text-align:center;background:#FFC000;height:20px;'><td nowrap colspan='3' align='center' style='border: 1px solid black;'>" + StringUtils.capitalize(reportType.toString().toLowerCase()) + " count ERP L2 tickets</td></tr>");
		body.append("<tr style='text-align:center;background:yellow;height:20px;'><td align='center' style='text-align:center;border: 1px solid black;'>Date</td><td align='center' style='text-align:center;border: 1px solid black;'>Count in BMC</td><td align='center' style='text-align:center;border: 1px solid black;'>Count in BMC-DB</td></tr>");
		body.append("<tr style='text-align:center;height:20px;'><td align='center' style='text-align:center;border: 1px solid black;'>" + dateFormat.format(date) + "</td><td align='center' style='text-align:center;border: 1px solid black;'>" + esicIncidents.size() + "</td><td align='center' style='text-align:center;border: 1px solid black;'>" + esicIncidents.size() + "</td></tr>");
		body.append("</table>");
		body.append("<br>");

		int columnCount = 0;
		long columnValues[] = new long[9];
		String[] columnNames = { "0-7 DAYS", "8-15 DAYS", "16-30 DAYS", "31-50 DAYS", "51-75 DAYS", "76-100 DAYS", "101-150 DAYS", "151-200 DAYS", "MORE THAN 200 DAYS" };
		for (int s = 0; s < columnNames.length; s++) {
			final String columnName = columnNames[s];
			columnValues[s] = esicIncidents.stream().filter(incident -> incident.getCLASSIFICATION_OF_DAYS().equals(columnName)).count();
		}

		for (long value : columnValues) {
			columnCount += ((value != 0) ? 1 : 0);
		}

		body.append("<table border='0' cellspacing='0' style='border-collapse:collapse;width:600px;font-size:0.8em;color:white'>");
		body.append("<tr style='text-align:center;background:#1F497D;height:20px;'><td nowrap colspan='" + ((columnCount > 3) ? (columnCount + 2) : 6) + "' align='center' style='border: 1px solid black;'>Overall Open Cases Count 09-Sept-2016 to " + dateFormat.format(new Date()) + " (Assigned , In Progress , Pending)</td></tr>");
		body.append("<tr style='text-align:center;background:#4F81BD;height:20px;'>");
		body.append("<td align='center' style='text-align:center;border: 1px solid black;'>Assigned Group</td>");
		if (columnCount > 4) {
			for (int i = 1; i <= columnCount; i++) {
				body.append("<td align='center' style='text-align:center;border: 1px solid black;'>" + StringUtils.capitalize(columnNames[i - 1].toLowerCase()) + "</td>");
			}
		} else {
			for (int i = 1; i <= 4; i++) {
				body.append("<td align='center' style='text-align:center;border: 1px solid black;'>" + StringUtils.capitalize(columnNames[i - 1].toLowerCase()) + "</td>");
			}
		}
		body.append("<td align='center' style='text-align:center;border: 1px solid black;'>Grand Total</td>");
		body.append("</tr>");
		body.append("<tr style='text-align:center;background:white;height:20px;'>");
		body.append("<td align='center' style='text-align:center;border: 1px solid black;color:black;'>ERP</td>");
		if (columnCount > 4) {
			for (int i = 1; i <= columnCount; i++) {
				body.append("<td align='center' style='text-align:center;border: 1px solid black;color:black;'>" + columnValues[i - 1] + "</td>");
			}
		} else {
			for (int i = 1; i <= 4; i++) {
				body.append("<td align='center' style='text-align:center;border: 1px solid black;color:black;'>" + columnValues[i - 1] + "</td>");
			}
		}
		body.append("<td align='center' style='text-align:center;border: 1px solid black;color:black;'>" + esicIncidents.size() + "</td>");
		body.append("</tr>");
		body.append("</table>");
		body.append("<br>");
		body.append("<p style='color:brown;font-size:0.8em'><b>Thanks & Regards,</b></p>");
		body.append("<p><span style='color:brown;font-size:0.8em'><b>Subramani B</b></span><span style='color:black;font-size:0.7em'> | <b>Trainee Associate Software Engineer <b> | <span style='color:brown;font-size:0.8em'>Mobile</span>  +91 9698292186 | </span><span style='color:brown;font-size:0.8em'> URL:</span><span style='color:brown;font-size:0.7em'> www.renaissance-it.com</span></p>");
		body.append("<p>This is an auto generated mail...</p>");
	}

	public String getMessageBody() {
		return body.toString();
	}

	public abstract boolean sendESICCountReportMail(ReportType reportType);
}
