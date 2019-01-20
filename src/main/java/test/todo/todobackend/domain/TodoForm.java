package test.todo.todobackend.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TodoForm {

  @NotBlank
  private String title;

  @NotNull
  private Boolean completed;
}