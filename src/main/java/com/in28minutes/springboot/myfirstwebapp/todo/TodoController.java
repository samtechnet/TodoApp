package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("List-todos")
    public String ListAllTodos(ModelMap model){
        List<Todo> todos= todoService.findByUsername("samuel");
        model.addAttribute("todos", todos);
        return "ListTodos";
    }
    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap model){
        String username= (String)model.get("name");
        Todo todo= new Todo(0,username, "", LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username= (String)model.get("name");
        todoService.addTodo(username,todo.getDescription(), LocalDate.now().plusYears(1), false);
        return "redirect:List-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodos(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:List-todos";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodosPage(@RequestParam int id, ModelMap model){
        System.out.println(id);
        Todo todo= todoService.findById(id);
       System.out.println(todo);
       model.addAttribute("todo", todo);
        return "todo";
    }
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username= (String)model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:List-todos";
    }
}
