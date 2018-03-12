package com.esic.bmc.export;

import java.io.File;
import java.util.List;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.model.ReportType;

public interface IExportESICIncidentList {
	public File generateCountReport(List<ESICIncident> esicIncidents, ReportType reportType);
}
