package com.fatih.report.demo.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fatih.report.demo.entity.Users;
import com.fatih.report.demo.repository.UserRepository;
import com.fatih.report.demo.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class UsersController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/listUsers")
	public String listUsers(Model model) {
		List<Users> listUsers = repository.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "listUsers";
	}
	
	@GetMapping("/users/new")
	public String showUsersNewForm(Model model) {
		model.addAttribute("users", new Users());
		
		return "users_form";
	}
	
	@PostMapping("/users/save")
	public String saveCategory(Users users) {
		repository.save(users);
		
		return "redirect:/listUsers";
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		service.exportReport(format);
		
		return "report";
	}
}
