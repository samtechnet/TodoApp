package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos=new ArrayList<>();
    private static int todoCount=0;
    static {
        todos.add(new Todo(++todoCount,"samuel", "learn AWS", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todoCount,"samuel", "learn DevOps", LocalDate.now().plusYears(2),false));
        todos.add(new Todo(++todoCount,"samuel", "learn Full Stack Development", LocalDate.now().plusYears(3),false));
        todos.add(new Todo(++todoCount,"samuel", "learn docker", LocalDate.now().plusYears(4),false));
    }
    public List<Todo> findByUsername(String username){
        return todos;
    }
    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo= new Todo(++todoCount, username, description, targetDate,done);
        todos.add(todo);
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate=todo -> todo.getId()==id;
    }
    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;

        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}