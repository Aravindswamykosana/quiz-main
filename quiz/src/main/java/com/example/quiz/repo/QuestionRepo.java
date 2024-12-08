package com.example.quiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.quiz.dto.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer>{
	List<Question> findQuestionByCatagory(String catagory);
	
	@Query(value = "SELECT * FROM question q WHERE q.catagory = :catagory", nativeQuery = true)
	List<Question> findRandomQuestionsByCatagory(String catagory);

}
