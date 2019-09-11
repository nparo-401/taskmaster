package com.nparo.TaskMaster.controllers;

import com.nparo.TaskMaster.models.History;
import com.nparo.TaskMaster.models.Tasks;
import com.nparo.TaskMaster.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
  
  @GetMapping("/users/{name}/tasks")
  public List<Tasks> getTasksForUser(@PathVariable String name) {
    return tasksRepository.findAllByAssignee(name);
  }
  
  @PostMapping("/tasks")
  public Tasks addNewTask(@RequestBody Tasks tasks) {
    Tasks t = new Tasks();
    t.setTitle(tasks.getTitle());
    t.setDescription(tasks.getDescription());
    t.setStatus("Available");
    t.setAssignee("none");
    History history = new History(new Date().toString(), t.getStatus());
    t.addHistory(history);
    tasksRepository.save(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/state")
  public Tasks updateTaskStatus(@PathVariable String id) {
    Tasks t = tasksRepository.findById(id).get();
    if (t.getStatus().equals("Assigned")) {
      t.setStatus("Accepted");
      History history = new History(new Date().toString(), t.getStatus());
      t.addHistory(history);
    } else if (t.getStatus().equals("Accepted")) {
      t.setStatus("Finished");
      History history = new History(new Date().toString(), t.getStatus());
      t.addHistory(history);
    }
    tasksRepository.save(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/assign/{assignee}")
  public Tasks addTaskAssignee(@PathVariable String id, @PathVariable String assignee) {
    Tasks t = tasksRepository.findById(id).get();
    t.setAssignee(assignee);
    t.setStatus("Assigned");
    History history = new History(new Date().toString(), t.getStatus());
    t.addHistory(history);
    tasksRepository.save(t);
    return t;
  }
  
  @DeleteMapping("/tasks/{id}/delete")
  public Tasks deleteTaskStatus(@PathVariable String id) {
    Tasks t = tasksRepository.findById(id).get();
    tasksRepository.delete(t);
    return t;
  }
}
