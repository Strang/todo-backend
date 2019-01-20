package test.todo.todobackend.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TodoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private ServerCode errorCode;
  private String errorMessage;

}