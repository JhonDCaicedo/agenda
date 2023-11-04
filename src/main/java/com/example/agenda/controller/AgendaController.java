package com.example.agenda.controller;

import com.example.agenda.service.TaskService;
import com.example.agenda.domain.Task;
import com.example.agenda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private TaskService service;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String initAgenda(Model model) {
        model.addAttribute("taskList", service.findAllTask());
        model.addAttribute("taskListEnd", service.findAllTaskFinalDate());
        return "taskList";
    }

    @GetMapping("/createTask")
    public String agendaCreateTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("categoryList", categoryService.findAllCategory());
        return "createTask";
    }

    @GetMapping("/eliminar/{id}")
    public ModelAndView agendaTaskDelete(@PathVariable(value = "id") Long id) {
        boolean res = service.deleteByIdTask(id);
        return new ModelAndView("redirect:/agenda/product");
    }

    @GetMapping("/update/{id}")
    public String agendaTaskUpdate(@PathVariable(value = "id") Long id, Model model) {
        Task task = service.findByIdTask(id);
        model.addAttribute("task", task);
        model.addAttribute("categoryList", categoryService.findAllCategory());
        return "createTask";
    }

    @PostMapping("/createTask")
    public ModelAndView agendaCreateProduct(@ModelAttribute Task task,
                                           @RequestParam("idCategory") Long idcategory,
                                           Model model) {
        model.addAttribute("task", task);
        model.addAttribute("taskList", categoryService.findAllCategory());
        Task t = service.saveTask(task);
        t.setCategory(categoryService.findByIdCategory(idcategory));
        System.out.println("Data: " + idcategory);
        service.saveTask(t);
        return new ModelAndView("redirect:/agenda");
    }


}
