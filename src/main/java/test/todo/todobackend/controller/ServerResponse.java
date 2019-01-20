package test.todo.todobackend.controller;

import lombok.Getter;

@Getter
public class ServerResponse<T> {

  private Integer status;
  private T data;

  private ServerResponse(ServerCode code, T data) {
    this.status = code.getCode();
    this.data = data;
  }

  public static <T> ServerResponse<T> OK(T data) {
    return new ServerResponse<>(ServerCode.OK, data);
  }

  public static <T> ServerResponse<T> ERROR(ServerCode errCode, T data) {
    return new ServerResponse<>(errCode, data);
  }
}