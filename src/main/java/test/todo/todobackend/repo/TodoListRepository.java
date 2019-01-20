package test.todo.todobackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import test.todo.todobackend.domain.Todo;

public interface TodoListRepository extends JpaRepository<Todo, Integer>, TodoListCustomizedRepo {

  @Modifying
  @Transactional
  @Query("delete Todo todolist where todolist.completed = true")
  void deleteCompleted();

}