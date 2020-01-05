package com.example.demo.todoapp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.todoapp.entities.TodoItem;

@Repository
//@RepositoryDefinition(domainClass = TodoItem.class, idClass = long.class)
public interface TodoItemRepository extends JpaRepository<TodoItem, Long>{

	List<TodoItem> findByDoneOrderByTitleAsc(boolean done);

	//TodoItem findOne(long id);

}
