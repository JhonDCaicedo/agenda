package com.example.agenda.controller;

import com.example.agenda.domain.Category;
import com.example.agenda.domain.Task;
import com.example.agenda.service.CategoryService;
import com.example.agenda.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @Autowired
    private TaskService taskService;
    @GetMapping
    public String categoryInit(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("categoryList", service.findAllCategory());
        return "list_createCategory";
    }

    @GetMapping("/eliminar/{id}")
    public ModelAndView categoryDelete (@PathVariable(value="id") Long id){
        //boolean res = service.deleteByIdCategory(id);
        Set<Task> lt = taskService.findAllTaskCategory(service.findByIdCategory(id));
        for (Task task : lt) {
            taskService.deleteByIdTask(task.getId());
        }
        service.deleteByIdCategory(id);
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/update/{id}")
    public String categoryUpdate (@PathVariable(value="id") Long id, Model model){
        Category category = service.findByIdCategory(id);
        model.addAttribute("category", category);
        return "list_createCategory";
    }

    @PostMapping
    public ModelAndView categoryProduct (@ModelAttribute Category category, Model model){
        model.addAttribute("category", category);
        Category p = service.saveCategory(category);
        return new ModelAndView("redirect:/category");
    }

}
