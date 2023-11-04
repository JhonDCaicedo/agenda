package com.example.agenda.service;

import com.example.agenda.domain.Category;
import com.example.agenda.domain.Task;
import com.example.agenda.repository.CategoryRepository;
import com.example.agenda.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAllTask(){
        return repository.findAll();
    }

    public Set<Task> findAllTaskCategory(Category  category){
        return repository.findByCategory(category);
    }

    public List<Task> findAllTaskFinalDate(){
        List<Task> lt = null;
        System.out.println(LocalDate.now());
        for (Task t: repository.findByFinalDate(new Date())) {
            assert false;
            boolean add = lt.add(t);
        }
        return lt;
    }
    public Task saveTask(Task task){
        return repository.save(task);
    }

    public boolean deleteByIdTask(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public Task findByIdTask(Long id){
        Task o = null;

        if(repository.findById(id).isPresent()){
            o = repository.findById(id).get();
        }

        return o;
    }
}
