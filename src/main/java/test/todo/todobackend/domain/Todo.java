package test.todo.todobackend.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "todolist")
@Data
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty("The database generated todo id")
  private Integer id;

  @Column
  private String title;

  @Column
  private boolean completed;

  @Column(name = "created_at")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

}