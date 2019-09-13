package com.nparo.TaskMaster.repository;

import com.nparo.TaskMaster.models.Task;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface TasksRepository extends CrudRepository<Task, String> {
  Optional<Task> findById(String id);
  List<Task> findByAssignee(String name);
}
