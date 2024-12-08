package com.example.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.dto.Question;
import com.example.quiz.exception.DataNotFoundException;
import com.example.quiz.exception.IdNotFoundException;
import com.example.quiz.service.QuestionService;
import com.example.quiz.util.ResponseStructure;

@RestController
@RequestMapping("questions")
public class QuestionController {
	@Autowired
	private QuestionService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Question>> saveQuestion(@RequestBody Question question){
		return service.saveQuestion(question);
	}
	
	@GetMapping("/getQuestion")
	public ResponseEntity<ResponseStructure<Question>> getQuestion(@RequestParam int id) throws IdNotFoundException{
		return service.getQuestion(id);
	}
	
	@GetMapping("/getAllQuestion")
	public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestion() throws IdNotFoundException, DataNotFoundException{
		return service.getAllQuestion();
	}
	
	@PostMapping("/updateQuestion")
	public ResponseEntity<ResponseStructure<Question>> updateQuestion(@RequestBody Question question) throws IdNotFoundException{
		return service.updateQuestion(question);
	}
	
	@GetMapping("/deleteQuestion")
	public ResponseEntity<ResponseStructure<Question>> deleteQuestion(@RequestParam int id) throws IdNotFoundException{
		return service.deleteQuestion(id);
	}
	
	@GetMapping("/getQuestionByCatagory")
	public ResponseEntity<ResponseStructure<List<Question>>> getQuestionByCatagory(@RequestParam String catagory) throws DataNotFoundException{
		return service.getQuestionByCatagory(catagory);
	}
	
	
	
}
