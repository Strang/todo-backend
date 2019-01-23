package test.todo.todobackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import test.todo.todobackend.domain.Todo;
import test.todo.todobackend.domain.TodoForm;
import test.todo.todobackend.controller.TodoException;
import test.todo.todobackend.repo.TodoListRepository;

@RestController
@RequestMapping(path = "/todo")
@Api(value="todolist", description="todolist backend for vue.js learning")
public class TodoListController {

  @Autowired
  private TodoListRepository todoListRepo;

  @GetMapping(path = "/all")
  @ApiOperation(value = "Get all todo items")
  public ServerResponse<List<Todo>> getAllTodos() {
    List<Todo> todos = todoListRepo.findAll();
    return ServerResponse.OK(todos);
  }

  @PostMapping(path = "/add/{title}")
  @ApiOperation(value = "Add a todo item")
  public ServerResponse<Todo> add(@PathVariable("title") String title) {
    Todo todo = new Todo();
    todo.setTitle(title);
    Todo savedTodo = todoListRepo.save(todo);
    return ServerResponse.OK(savedTodo);
  }

  @PostMapping(path = "/delete/{id}")
  @ApiOperation(value = "Delete one todo item by id")
  public ServerResponse<String> deleteById(@PathVariable("id") Integer id) {
    checkId(id);
    todoListRepo.deleteById(id);
    return ServerResponse.OK("delete success");
  }

  @PostMapping(path = "/update/{id}")
  @ApiOperation(value = "Update one todo item by id")
  public ServerResponse<Todo> updateTodo(@PathVariable("id") Integer id, @RequestBody TodoForm todoForm) {
    checkId(id);

    /**
     * getOne() method gets you only a reference (proxy) object and does not fetch
     * it from the DB. see in:
     * https://stackoverflow.com/questions/39741102/how-to-beautifully-update-a-jpa-entity-in-spring-data/39746931
     */
    Todo todo = todoListRepo.getOne(id);
    todo.setTitle(todoForm.getTitle());
    todo.setCompleted(todoForm.getCompleted());
    Todo updatedTodo = todoListRepo.save(todo);
    return ServerResponse.OK(updatedTodo);
  }

  @PostMapping(path = "/deleteCompleted")
  @ApiOperation(value = "Delete all completed todo items")
  public ServerResponse<String> deleteCompleted() {
    todoListRepo.deleteCompleted();
    return ServerResponse.OK("delete success");
  }

  @PostMapping(path = "/checkOrUncheckAll")
  @ApiOperation(value = "Check or uncheck all todo items")
  public ServerResponse<String> checkAll(@RequestParam("checked") boolean checked) {
    todoListRepo.checkAll(checked);
    return ServerResponse.OK("success");

  }

  private void checkId(Integer id) {
    if (id < 0) {
      throw new TodoException(ServerCode.INVALID_PARAM, "id cannot be negative");
    }
    if (!todoListRepo.existsById(id)) {
      throw new TodoException(ServerCode.INVALID_PARAM, "id not exist");
    }
  }

}