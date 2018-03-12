package com.esic.bmc.export;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.esic.bmc.ScheduledTasks;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.model.ReportType;

@Component(value = "exportESICIncidentList")
public class ExportESICIncidentList implements IExportESICIncidentList {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public File generateCountReport(List<ESICIncident> esicIncidents, ReportType reportType) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		generateSheets(workbook, reportType);
		generateCountChart(workbook, esicIncidents, reportType);
		generateCountReport(workbook, esicIncidents, reportType);
		return exportFile(getFileName(reportType), workbook);
	}

	private String getFileName(ReportType reportType) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return "BMC_Count/" + StringUtils.capitalize(reportType.toString().toLowerCase()) + "_Count/" + localDate.getYear() + "/" + localDate.getMonthValue() + "/" + StringUtils.capitalize(reportType.toString().toLowerCase()) + "_Count(" + dateFormat.format(date) + ").xlsx";
	}

	private void generateSheets(XSSFWorkbook workbook, ReportType reportType) {
		try {
			workbook.createSheet(StringUtils.capitalize(reportType.toString().toLowerCase()) + "_Incident_Column_Status");
			workbook.createSheet(StringUtils.capitalize(reportType.toString().toLowerCase()) + "_Incident_List");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	private void generateCountChart(XSSFWorkbook workbook, List<ESICIncident> esicIncidents, ReportType reportType) {
		try {
			XSSFSheet incidentSheet = workbook.getSheet(StringUtils.capitalize(reportType.toString().toLowerCase()) + "_Incident_Column_Status");

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

			Font font = workbook.createFont();
			font.setColor(IndexedColors.WHITE.getIndex());

			XSSFCellStyle headerBox1Line1 = workbook.createCellStyle();
			headerBox1Line1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			headerBox1Line1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerBox1Line1.setAlignment(HorizontalAlignment.CENTER);
			headerBox1Line1.setVerticalAlignment(VerticalAlignment.CENTER);

			XSSFCellStyle headerBox1Line2 = workbook.createCellStyle();
			headerBox1Line2.setAlignment(HorizontalAlignment.CENTER);
			headerBox1Line2.setVerticalAlignment(VerticalAlignment.CENTER);
			headerBox1Line2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			headerBox1Line2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			setCellBorder(headerBox1Line2);

			XSSFRow row = incidentSheet.createRow(5);
			XSSFCell cell = row.createCell(5);
			cell.setCellValue(StringUtils.capitalize(reportType.toString().toLowerCase()) + " count ERP L2 tickets.");
			cell.setCellStyle(headerBox1Line1);

			row = incidentSheet.createRow(6);
			cell = row.createCell(5);
			cell.setCellValue("Date");
			cell.setCellStyle(headerBox1Line2);

			cell = row.createCell(6);
			cell.setCellValue("Count in BMC");
			cell.setCellStyle(headerBox1Line2);

			cell = row.createCell(7);
			cell.setCellValue("Count in BMC-DB");
			cell.setCellStyle(headerBox1Line2);

			XSSFCellStyle headerBoxLine3 = workbook.createCellStyle();
			headerBoxLine3.setAlignment(HorizontalAlignment.CENTER);
			headerBoxLine3.setVerticalAlignment(VerticalAlignment.CENTER);
			headerBoxLine3.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			headerBoxLine3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			setCellBorder(headerBoxLine3);

			row = incidentSheet.createRow(7);
			cell = row.createCell(5);
			cell.setCellValue(dateFormat.format(new Date()));
			cell.setCellStyle(headerBoxLine3);

			cell = row.createCell(6);
			cell.setCellValue((esicIncidents != null) ? esicIncidents.size() : 0);
			cell.setCellStyle(headerBoxLine3);

			cell = row.createCell(7);
			cell.setCellValue((esicIncidents != null) ? esicIncidents.size() : 0);
			cell.setCellStyle(headerBoxLine3);

			CellRangeAddress range = new CellRangeAddress(5, 5, 5, 7);
			incidentSheet.addMergedRegion(range);
			setMergedCellBorder(range, incidentSheet);

			XSSFCellStyle headerBox2Line1 = workbook.createCellStyle();
			headerBox2Line1.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
			headerBox2Line1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerBox2Line1.setAlignment(HorizontalAlignment.CENTER);
			headerBox2Line1.setVerticalAlignment(VerticalAlignment.CENTER);
			headerBox2Line1.setFont(font);

			XSSFCellStyle headerBox2Line2 = workbook.createCellStyle();
			headerBox2Line2.setAlignment(HorizontalAlignment.CENTER);
			headerBox2Line2.setVerticalAlignment(VerticalAlignment.CENTER);
			headerBox2Line2.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			headerBox2Line2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			setCellBorder(headerBox2Line2);
			headerBox2Line2.setFont(font);

			row = incidentSheet.createRow(9);
			cell = row.createCell(5);
			cell.setCellValue("Overall Open Cases Count 09-09-2016 to " + dateFormat.format(new Date()) + " (Assigned , In Progress , Pending)");
			cell.setCellStyle(headerBox2Line1);

			range = new CellRangeAddress(9, 9, 5, 5 + ((columnCount > 3) ? (columnCount + 1) : 5));
			incidentSheet.addMergedRegion(range);
			setMergedCellBorder(range, incidentSheet);

			row = incidentSheet.createRow(10);
			cell = row.createCell(5);
			cell.setCellValue("Assigned Group");
			cell.setCellStyle(headerBox2Line2);
			if (columnCount > 4) {
				for (int i = 1; i <= columnCount; i++) {
					cell = row.createCell(5 + i);
					cell.setCellValue(columnNames[i - 1]);
					cell.setCellStyle(headerBox2Line2);
				}

				cell = row.createCell(5 + ((columnCount > 3) ? (columnCount + 1) : 5));
				cell.setCellValue("Grand Total");
				cell.setCellStyle(headerBox2Line2);

				row = incidentSheet.createRow(11);
				cell = row.createCell(5);
				cell.setCellValue("ERP");
				cell.setCellStyle(headerBoxLine3);

				for (int i = 1; i <= columnCount; i++) {
					cell = row.createCell(5 + i);
					cell.setCellValue(columnValues[i - 1]);
					cell.setCellStyle(headerBoxLine3);
				}

				cell = row.createCell(5 + ((columnCount > 3) ? (columnCount + 1) : 5));
				cell.setCellValue(esicIncidents.stream().count());
				cell.setCellStyle(headerBoxLine3);
			} else {
				for (int i = 1; i <= 4; i++) {
					cell = row.createCell(5 + i);
					cell.setCellValue(columnNames[i - 1]);
					cell.setCellStyle(headerBox2Line2);
				}

				cell = row.createCell(5 + ((columnCount > 3) ? (columnCount + 1) : 5));
				cell.setCellValue("Grand Total");
				cell.setCellStyle(headerBox2Line2);

				row = incidentSheet.createRow(11);
				cell = row.createCell(5);
				cell.setCellValue("ERP");
				cell.setCellStyle(headerBoxLine3);

				for (int i = 1; i <= 4; i++) {
					cell = row.createCell(5 + i);
					cell.setCellValue(columnValues[i - 1]);
					cell.setCellStyle(headerBoxLine3);
				}

				cell = row.createCell(5 + ((columnCount > 3) ? (columnCount + 1) : 5));
				cell.setCellValue(esicIncidents.stream().count());
				cell.setCellStyle(headerBoxLine3);
			}
			IntStream.range(5, 5 + ((columnCount > 4) ? (columnCount + 2) : 6)).forEach((columnIndex) -> incidentSheet.autoSizeColumn(columnIndex));
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

	private void generateCountReport(XSSFWorkbook workbook, List<ESICIncident> esicIncidents, ReportType reportType) {
		try {
			XSSFSheet incidentSheet = workbook.getSheet(StringUtils.capitalize(reportType.toString().toLowerCase()) + "_Incident_List");
			XSSFRow row = incidentSheet.createRow(0);
			Field[] fields = ESICIncident.class.getDeclaredFields();

			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				XSSFCell cell = row.createCell(j);
				cell.setCellStyle(getHeaderFormatting(workbook));
				cell.setCellValue(fields[j].getName().toUpperCase());
			}

			for (int i = 0; i < esicIncidents.size(); i++) {
				row = incidentSheet.createRow(i + 1);
				for (int j = 0; j < fields.length; j++) {
					Method method = esicIncidents.get(i).getClass().getDeclaredMethod("get" + fields[j].getName().substring(0, 1).toUpperCase() + fields[j].getName().substring(1, fields[j].getName().length()));
					XSSFCell cell = row.createCell(j);
					cell.setCellValue((String) method.invoke(esicIncidents.get(i)));
					cell.setCellStyle((i % 2 == 1) ? getAlternateColorRow(getBorderFormatting(workbook)) : getBorderFormatting(workbook));
					incidentSheet.autoSizeColumn(cell.getColumnIndex());
				}
			}

			IntStream.range(0, fields.length).forEach((columnIndex) -> incidentSheet.autoSizeColumn(columnIndex));
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

	private XSSFCellStyle getAlternateColorRow(XSSFCellStyle cellStyle) {
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getHeaderFormatting(XSSFWorkbook workbook) {
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setCellBorder(headerStyle);

		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(font);
		return headerStyle;
	}

	private XSSFCellStyle getBorderFormatting(XSSFWorkbook workbook) {
		XSSFCellStyle borderStyle = workbook.createCellStyle();
		setCellBorder(borderStyle);
		return borderStyle;
	}

	private void setCellBorder(XSSFCellStyle cellStyle) {
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	}

	private void setMergedCellBorder(CellRangeAddress range, XSSFSheet incidentSheet) {
		RegionUtil.setBorderTop(BorderStyle.THIN, range, incidentSheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, range, incidentSheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, range, incidentSheet);
		RegionUtil.setBorderBottom(BorderStyle.THIN, range, incidentSheet);
	}

	private File exportFile(String fileName, XSSFWorkbook workbook) {
		File esicCountFile = null;
		try {
			esicCountFile = new File(fileName);
			esicCountFile.getParentFile().mkdirs();
			FileOutputStream fileOut = new FileOutputStream(esicCountFile);
			workbook.write(fileOut);
			workbook.close();
			fileOut.flush();
			fileOut.close();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			esicCountFile = null;
		}
		return esicCountFile;
	}

}
