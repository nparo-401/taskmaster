package com.nparo.TaskMaster.repository;

import com.nparo.TaskMaster.models.Tasks;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface TasksRepository extends CrudRepository<Tasks, String> {
  Optional<Tasks> findById(String id);
  List<Tasks> findAllByAssignee(String name);
}
