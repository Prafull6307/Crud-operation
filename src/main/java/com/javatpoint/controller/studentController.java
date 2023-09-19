package com.javatpoint.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javatpoint.*;
import com.javatpoint.model.student;
import com.javatpoint.repository.studentRepository;


	@Controller
	@RequestMapping("/students/")
	public class studentController {

	    private final studentRepository studentRepository;

	    @Autowired
	    public studentController(studentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	    }

	    @GetMapping("signup")
	    public String showSignUpForm(student student) {
	        return "add-student";
	    }

	    @GetMapping("list")
	    public String showUpdateForm(Model model , student student) {
	    	studentRepository.save(student);
	        model.addAttribute("students", studentRepository.findAll());
	        return "index";
	    }

	    @PostMapping("add")
	    public String addStudent(@Validated student student, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "add-student";
	        }

	        studentRepository.save(student);
	        return "redirect:list";
	    }

	    @GetMapping("edit/{id}")
	    public String showUpdateForm(@PathVariable("id") long id, Model model) {
	        student student = this.studentRepository.findById(id).get();
	        model.addAttribute("student", student);
	        return "update-student";
	    }

	    @PostMapping("update/{id}")
	    public String updateStudent(@PathVariable("id") long id, @Validated student student, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            student.setId(id);
	            return "update-student";
	        }

	        studentRepository.save(student);
	        model.addAttribute("students", studentRepository.findAll());
	        return "index";
	    }

	    @GetMapping("delete/{id}")
	    public String deleteStudent(@PathVariable("id") long id, Model model) {
	        student student = studentRepository.findById(id).get();
	          
	        studentRepository.delete(student);
	        model.addAttribute("students", studentRepository.findAll());
	        return "index";
	    }
	}


