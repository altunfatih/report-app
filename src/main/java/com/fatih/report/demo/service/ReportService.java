package com.fatih.report.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fatih.report.demo.entity.Users;
import com.fatih.report.demo.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	UserRepository repository;

	public void exportReport(String reportFormat) throws FileNotFoundException, JRException {
		String path = "C:\\Users\\fatih\\Desktop\\Report";
		
		List<Users> users = repository.findAll();
		
		File file = ResourceUtils.getFile("classpath:users.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Fatih Altun");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		if (reportFormat.equalsIgnoreCase("html"))
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users.html");
		
		if (reportFormat.equalsIgnoreCase("pdf"))
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users.pdf");

	}
}
