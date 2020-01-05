package com.example.demo.todoapp.TodoController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.todoapp.entities.TodoItem;
import com.example.demo.todoapp.forms.TodoItemForm;
import com.example.demo.todoapp.repositories.TodoItemRepository;

@Controller
public class HomeController {

	@Autowired
	TodoItemRepository repository;
	
	@RequestMapping
	public String index(@ModelAttribute TodoItemForm todoItemForm, @RequestParam("isDone") Optional<Boolean> isDone) {
		todoItemForm.setDone(isDone.isPresent() ? isDone.get() : false);
		todoItemForm.setTodoItems(this.repository.findByDoneOrderByTitleAsc(todoItemForm.isDone()));
		return "index";
	}
	
	@PostMapping("/done")
	public String done(@RequestParam("id") long id) {
		TodoItem item = this.repository.findById(id).get();
		item.setDone(true);
		this.repository.save(item);
		return "redirect:/?isDone=false";
	}
	
	@PostMapping("/restore")
	public String restore(@RequestParam("id") long id) {
		TodoItem item = this.repository.findById(id).get();
		item.setDone(false);
		this.repository.save(item);
		return "redirect:/?isDone=true";
	}
		
	@PostMapping("/new")
	public String newItem(TodoItem item) {
		item.setDone(false);
		this.repository.save(item);
		return "redirect:/";
	}
}
