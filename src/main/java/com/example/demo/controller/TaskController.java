package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Task;
import com.example.demo.repository.TestRepository;

@Controller
public class TaskController {
	private final TestRepository repository;
	
	public TaskController(TestRepository repository) {
		this.repository= repository;
	}
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("tasks",
				repository.findAll()
				);
		
		return "task-list";
	}
	
	@PostMapping("/add")
	public String addTask(
			@RequestParam String title,
			@RequestParam String status
			) {
		Task task = new Task();
		
		task.setTitle(title);
		task.setStatus(status);
		
		repository.save(task);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id,
			Model model) {
		
		Task task=
				repository.findById(id).orElseThrow();
		
		model.addAttribute("task",task);
		
		return "task-edit";
	}
	
	@PostMapping("/update")
	public String updateTask(
			@RequestParam Long id,
			@RequestParam String title,
			@RequestParam String status) {
		
		Task task =
				repository.findById(id).orElseThrow();
		
		task.setTitle(title);
		task.setStatus(status);
		
		repository.save(task);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		
		repository.deleteById(id);
		
		return "redirect:/";
	}
}
