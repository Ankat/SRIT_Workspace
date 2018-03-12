package com.esic.bmc.mail;

import java.io.File;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.model.ReportType;
import com.esic.bmc.util.Resource;

@Component(value = "esicCountReportMail")
public class ESICCountReportMail extends AbstractMailCompose implements IESICCountReportMail {

	@Autowired
	private Resource resource;

	@Override
	public boolean sendESICCountReportMail(List<ESICIncident> esicIncidents, File esicCountReportFile, ReportType reportType) {
		setAttachmentFile(esicCountReportFile);
		setMessageBody(esicIncidents, reportType);
		return sendESICCountReportMail(reportType);
	}

	@Override
	public boolean sendESICCountReportMail(ReportType reportType) {
		boolean flag = false;
		try {
			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(resource.getProperty("mail.from.address")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(resource.getProperty("mail.to.address")));
			message.setSubject(getEmailSubject(reportType));
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(getMessageBody(), "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(getAttachmentFile().getAbsolutePath());
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(getAttachmentFile().getName());
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");
			flag = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			flag = false;
		}
		return flag;
	}

}
