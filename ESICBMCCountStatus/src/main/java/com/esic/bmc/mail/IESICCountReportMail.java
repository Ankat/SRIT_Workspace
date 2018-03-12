package com.esic.bmc.mail;

import java.io.File;
import java.util.List;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.model.ReportType;

public interface IESICCountReportMail {
	public boolean sendESICCountReportMail(List<ESICIncident> esicIncidents, File esicCountReportFile, ReportType reportType);
}
