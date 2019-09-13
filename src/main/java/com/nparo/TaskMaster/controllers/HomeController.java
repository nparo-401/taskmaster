package com.nparo.TaskMaster.controllers;

import com.nparo.TaskMaster.models.Task;
import com.nparo.TaskMaster.repository.S3Client;
import com.nparo.TaskMaster.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class HomeController {
  @Autowired
  TasksRepository tasksRepository;
  
  private S3Client s3Client;
  
  @Autowired
  HomeController(S3Client s3Client) {
    this.s3Client = s3Client;
  }
  
  @GetMapping("/")
  public String getHome() {
    return "index";
  }
  
  @GetMapping("/tasks")
  public List getTasks() {
    return (List)tasksRepository.findAll();
  }
  
  @GetMapping("/tasks/{id}")
  public Task getSingleTask(@PathVariable String id) {
    return tasksRepository.findById(id).get();
  }
  
  @GetMapping("/users/{name}/tasks")
  public List<Task> getTasksForUser(@PathVariable String name) {
    return tasksRepository.findByAssignee(name);
  }
  
  @PostMapping("/tasks")
  public Task addNewTask(@RequestBody Task task) {
    Task t = new Task(task.getId(), task.getTitle(), task.getDescription(), "Available", "none");
    t.addHistory();
    tasksRepository.save(t);
    return t;
  }
  
  @PostMapping("/tasks/{id}/image")
  public Task addImageToTask(@PathVariable String id, @RequestPart(value = "file") MultipartFile file) {
    String pic = this.s3Client.uploadFile(file);
    Task t = tasksRepository.findById(id).get();
    t.setImage(pic);
    tasksRepository.save(t);
    return t;
  }
  
  @DeleteMapping("/tasks/{id}")
  public Task deleteTaskStatus(@PathVariable String id) {
    Task t = tasksRepository.findById(id).get();
    tasksRepository.delete(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/state")
  public Task updateTaskStatus(@PathVariable String id) {
    Task t = tasksRepository.findById(id).get();
    if (t.getStatus().equals("Assigned")) {
      t.setStatus("Accepted");
      t.addHistory();
    } else if (t.getStatus().equals("Accepted")) {
      t.setStatus("Finished");
      t.addHistory();
    }
    tasksRepository.save(t);
    return t;
  }
  
  @PutMapping("/tasks/{id}/assign/{assignee}")
  public Task addTaskAssignee(@PathVariable String id, @PathVariable String assignee) {
    Task t = tasksRepository.findById(id).get();
    t.setAssignee(assignee);
    t.setStatus("Assigned");
    t.addHistory();
    tasksRepository.save(t);
    return t;
  }
}
