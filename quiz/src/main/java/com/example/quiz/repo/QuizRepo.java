package com.example.quiz.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz.dto.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
