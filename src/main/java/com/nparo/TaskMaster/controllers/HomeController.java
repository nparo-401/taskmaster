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
  public List getTasks() {
    return (List)tasksRepository.findAll();
  }
  
  @GetMapping("/users/{name}/tasks")
  public List<Tasks> getTasksForUser(@PathVariable String name) {
    return tasksRepository.findByAssignee(name);
  }
  
  @PostMapping("/tasks")
  public Tasks addNewTask(@RequestBody Tasks tasks) {
    Tasks t = new Tasks(tasks.getId(), tasks.getTitle(), tasks.getDescription(), "Available", "none");
    t.addHistory();
//    historySetter(t);
    tasksRepository.save(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/state")
  public Tasks updateTaskStatus(@PathVariable String id) {
    Tasks t = tasksRepository.findById(id).get();
    if (t.getStatus().equals("Assigned")) {
      t.setStatus("Accepted");
      t.addHistory();
//      historySetter(t);
    } else if (t.getStatus().equals("Accepted")) {
      t.setStatus("Finished");
      t.addHistory();
//      historySetter(t);
    }
    tasksRepository.save(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/assign/{assignee}")
  public Tasks addTaskAssignee(@PathVariable String id, @PathVariable String assignee) {
    Tasks t = tasksRepository.findById(id).get();
    t.setAssignee(assignee);
    t.setStatus("Assigned");
    t.addHistory();
//    historySetter(t);
    tasksRepository.save(t);
    return t;
  }
  
  @DeleteMapping("/tasks/{id}/delete")
  public Tasks deleteTaskStatus(@PathVariable String id) {
    Tasks t = tasksRepository.findById(id).get();
    tasksRepository.delete(t);
    return t;
  }
  
//  Helper Method
  private void historySetter(Tasks t) {
//    History history = new History(new Date().toString(), t.getStatus());
//    t.addHistory(history);
  }
}
