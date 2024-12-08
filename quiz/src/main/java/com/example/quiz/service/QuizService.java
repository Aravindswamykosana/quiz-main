package com.example.quiz.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz.dao.QuizDao;
import com.example.quiz.dto.Question;
import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.QuizAnswerResponse;
import com.example.quiz.dto.QuizWrapper;
import com.example.quiz.exception.DataNotFoundException;
import com.example.quiz.exception.IdNotFoundException;
import com.example.quiz.util.ResponseStructure;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Service
public class QuizService {
	
	@Autowired
	private QuizDao dao;
	
	public ResponseEntity<ResponseStructure<Quiz>> createQuiz(String catagory,String title){
		 Quiz db = dao.createQuiz(catagory,title);
		try {
			if(db!=null) {
				ResponseStructure<Quiz> rs=new ResponseStructure<Quiz>();
				rs.setData(db);
				rs.setMessage("quiz is created...!");
				rs.setStatus(HttpStatus.OK.value());
				rs.setDt(LocalDateTime.now());
				return new ResponseEntity<ResponseStructure<Quiz>>(rs,HttpStatus.OK);
			}
			else {
				throw new DataNotFoundException();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<ResponseStructure<Quiz>>(HttpStatus.BAD_REQUEST);
				
	}

	public ResponseEntity<ResponseStructure<List<QuizWrapper>>> getQuiz(int id) throws DataNotFoundException {
		 List<QuizWrapper> db = dao.getQuiz(id);
		if(db!=null) {
			ResponseStructure<List<QuizWrapper>> rs=new ResponseStructure<List<QuizWrapper>>();
			rs.setData(db);
			rs.setMessage("data is fetched succesfully...!");
			rs.setDt(LocalDateTime.now());
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<QuizWrapper>>>(rs,HttpStatus.OK);
		}
		else {
			throw new DataNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Quiz>> deleteQuiz(int id) throws IdNotFoundException{
		Quiz db = dao.deleteQuiz(id);
		if(db!=null) {
			ResponseStructure<Quiz> rs=new ResponseStructure<Quiz>();
			rs.setData(db);
			rs.setMessage("data is deleted succesfully...!");
			rs.setDt(LocalDateTime.now());
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Quiz>>(rs,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Integer>> getQuizAnswer(int id, List<QuizAnswerResponse> response) throws DataNotFoundException {
		int db = dao.getQuizAnswer(id,response);
		if(db!=0) {
			ResponseStructure<Integer>rs=new ResponseStructure<Integer>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setMessage("correction is done...! and your score =="+rs.getData());
			return new ResponseEntity<ResponseStructure<Integer>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new DataNotFoundException();
		}
	}
}
