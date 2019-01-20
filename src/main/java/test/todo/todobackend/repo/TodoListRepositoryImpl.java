package test.todo.todobackend.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public class TodoListRepositoryImpl implements TodoListCustomizedRepo {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  @Modifying
  public void checkAll(boolean checked) {
    StringBuilder sql = new StringBuilder();
    sql.append("update todolist ");
    sql.append("set completed = :checked, updated_at = now() ");
    em.createNativeQuery(sql.toString()).setParameter("checked", checked).executeUpdate();
  }

}