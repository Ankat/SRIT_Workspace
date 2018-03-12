package com.esic.bmc;

//	* "0 0 * * * *" = the top of every hour of every day.
//	* "*/10 * * * * *" = every ten seconds.
//	* "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//	* "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//	* "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//	* "0 0 0 25 12 ?" = every Christmas Day at midnight
//	second, minute, hour, day of month, month, day(s) of week
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.esic.bmc.export.ExportESICIncidentList;
import com.esic.bmc.mail.ESICCountReportMail;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.model.ReportType;
import com.esic.bmc.service.ESICIncidentService;

@Component(value = "scheduledTasks")
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private ESICIncidentService esicIncidentService;

	@Autowired
	private ExportESICIncidentList exportESICIncidentList;

	@Autowired
	private ESICCountReportMail esicCountReportMail;

	@Scheduled(cron = "0 0 8 * * *") // daily 8 AM
	public void openingCountStatus() {
		log.info("Opening Count Status : {}", dateFormat.format(new Date()));
		try {
			List<ESICIncident> esicIncidents = esicIncidentService.getESICIncidentList();
			File esicCountReportFile = exportESICIncidentList.generateCountReport(esicIncidents, ReportType.OPENING);
			esicCountReportMail.sendESICCountReportMail(esicIncidents, esicCountReportFile, ReportType.OPENING);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	@Scheduled(cron = "0 0 22 * * *") // daily 10 PM
	public void closingCountStatus() {
		log.info("Closing Count Status : {}", dateFormat.format(new Date()));
		try {
			List<ESICIncident> esicIncidents = esicIncidentService.getESICIncidentList();
			File esicCountReportFile = exportESICIncidentList.generateCountReport(esicIncidents, ReportType.CLOSING);
			esicCountReportMail.sendESICCountReportMail(esicIncidents, esicCountReportFile, ReportType.CLOSING);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	/*
	@Scheduled(fixedRate = 100000)
	public void test() {
		log.info("test : {}", dateFormat.format(new Date()));
		try {
			List<ESICIncident> esicIncidents = esicIncidentService.getESICIncidentList();
			File esicCountReportFile = exportESICIncidentList.generateCountReport(esicIncidents, ReportType.OPENING);
			esicCountReportMail.sendESICCountReportMail(esicIncidents, esicCountReportFile, ReportType.OPENING);
			System.out.println("Done");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
	*/

}
