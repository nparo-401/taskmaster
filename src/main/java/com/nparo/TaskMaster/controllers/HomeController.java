package com.nparo.TaskMaster.controllers;

import com.nparo.TaskMaster.models.Tasks;
import com.nparo.TaskMaster.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class HomeController {
  @Autowired
  TasksRepository tasksRepository;
  
  @GetMapping("/")
  public String getHome() {
    return "index";
  }
  
  @GetMapping("/tasks")
  public List<Tasks> getCustomers() {
    return (List) tasksRepository.findAll();
  }
  
  @PostMapping("/tasks")
  public Tasks addNewTask(@RequestBody Tasks tasks) {
    Tasks t = new Tasks();
    t.setTitle(tasks.getTitle());
    t.setDescription(tasks.getDescription());
    t.setStatus("available");
    tasksRepository.save(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/state")
  public Tasks updateTaskStatus(@PathVariable String id) {
    Tasks t = tasksRepository.findById(id).get();
    if (t.getStatus().equals("available")) {
      t.setStatus("assigned");
    } else if (t.getStatus().equals("assigned")) {
      t.setStatus("accepted");
    } else if (t.getStatus().equals("accepted")) {
      t.setStatus("finished");
    }
    tasksRepository.save(t);
    return t;
  }
}
