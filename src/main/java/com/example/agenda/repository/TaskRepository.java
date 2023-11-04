package com.example.agenda.repository;

import com.example.agenda.domain.Category;
import com.example.agenda.domain.Task;
import com.example.agenda.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Set<Task> findByCategory(Category category);
    Set<Task> findByFinalDate(Date finalDate);
}
