package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.controller.TaskController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class SpreadsheetService {
	
	private final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	
	@Value("${fileupload.path}")
	private String fileuploadPath;
	
	@Value("${web.static.resources.stylesheet}")
	private String stylesheetPath;
	
	@Value("${web.static.resources.stylesheet.extension}")
	private String stylesheetExtension;
	
	public String createActivityReport(ProjectDto project, List<ActivityDto> activities) throws IOException {
		if (activities.isEmpty()) throw new IllegalArgumentException("activities list is empty");
		
		String name = "activities-report_" +
				DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now());
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(name);
		
		Row headerRow = sheet.createRow(0);
		
		List<String> headerNames = Arrays.asList("id", "taskId", "taskName", "creatorLogin", "assigneeLogin", "status", "description", "elapsed");
		
		for (int i = 0; i < headerNames.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headerNames.get(i));
		}
		
		for (int i = 0; i < activities.size(); i++) {
			ActivityDto a = activities.get(i);
			
			Row row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(a.id);
			row.createCell(1).setCellValue(a.task.id);
			row.createCell(2).setCellValue(a.task.name);
			row.createCell(3).setCellValue(a.creator.login);
			if (a.assignee != null) row.createCell(4).setCellValue(a.assignee.login);
			row.createCell(5).setCellValue(a.status.toString());
			row.createCell(6).setCellValue(a.description);
			if (a.elapsed != null) row.createCell(7).setCellValue(a.elapsed);
		}
		
		for (int i = 0; i < 8; i++) {
			sheet.autoSizeColumn(i);
		}
		
		String filePath = fileuploadPath + stylesheetPath + project.id + "/" + name + stylesheetExtension;
		
		new File(filePath).getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		
		LOG.info("saved spreadsheet: " + name + " at " + filePath);
		
		return name;
	}
	
}
