package test.todo.todobackend.controller;

import lombok.Getter;

@Getter
public enum ServerCode {

  OK(0),
  UNKNOWN_ERROR(-1),
  INVALID_PARAM(1);

  private Integer code;

  ServerCode(Integer code) {
    this.code = code;
  }
}