package com.app.todo.controller;

import com.app.todo.entity.Task;
import com.app.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    TaskRepository repository;

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", repository.findAll());
        return "list";
    }

    @GetMapping("/tasks/add")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    @PostMapping("/tasks/save")
    public String addTask(@ModelAttribute Task task, Model model) {

        try {
            repository.save(task);
            return "redirect:/tasks";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "add";
        }
    }

}
