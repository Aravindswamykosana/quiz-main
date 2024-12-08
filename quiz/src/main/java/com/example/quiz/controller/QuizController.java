package com.example.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.QuizAnswerResponse;
import com.example.quiz.dto.QuizWrapper;
import com.example.quiz.exception.DataNotFoundException;
import com.example.quiz.exception.IdNotFoundException;
import com.example.quiz.service.QuizService;
import com.example.quiz.util.ResponseStructure;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	private QuizService service;
	
	@GetMapping("/createQuiz")
	public ResponseEntity<ResponseStructure<Quiz>> createQuiz(@RequestParam String catagory,@RequestParam String title){
		
		return service.createQuiz(catagory,title);
	}
	
	@GetMapping("/getQuiz")
	public ResponseEntity<ResponseStructure<List<QuizWrapper>>> getQuiz(@RequestParam int id) throws DataNotFoundException{
		return service.getQuiz(id);
	}
	
	@GetMapping("/deleteQuiz")
	public ResponseEntity<ResponseStructure<Quiz>> deleteQuiz(@RequestParam int id) throws IdNotFoundException{
		return service.deleteQuiz(id);
	}
	
	@PostMapping("/quizAnswer/{id}") //we have to use pathvariable here bcoz of based on id and post method
	public ResponseEntity<ResponseStructure<Integer>> quizAnswer(@PathVariable Integer id, @RequestBody List<QuizAnswerResponse> response) throws DataNotFoundException {
	    return service.getQuizAnswer(id, response);
	}

	
}
