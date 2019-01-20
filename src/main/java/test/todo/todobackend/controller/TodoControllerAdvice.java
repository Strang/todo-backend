package test.todo.todobackend.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import test.todo.todobackend.controller.TodoException;

@ControllerAdvice
public class TodoControllerAdvice {

  @ExceptionHandler(TodoException.class)
  @ResponseBody
  public ServerResponse<String> todoExceptionHandler(TodoException exception) {
    return ServerResponse.ERROR(exception.getErrorCode(), exception.getErrorMessage());
  }
}