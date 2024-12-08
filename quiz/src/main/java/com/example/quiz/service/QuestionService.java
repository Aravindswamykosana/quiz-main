package com.example.quiz.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz.dao.QuestionDao;
import com.example.quiz.dto.Question;
import com.example.quiz.exception.DataNotFoundException;
import com.example.quiz.exception.IdNotFoundException;
import com.example.quiz.util.ResponseStructure;

@Service
public class QuestionService {
	@Autowired
	private QuestionDao dao;
	
	public ResponseEntity<ResponseStructure<Question>> saveQuestion(Question question){
		Question q = dao.saveQuestion(question);
		ResponseStructure<Question> rs=new ResponseStructure<Question>();
		rs.setData(q);
		rs.setDt(LocalDateTime.now());
		rs.setMessage("your data saved succesfully in our database...!");
		rs.setStatus(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Question>> getQuestion(int id) throws IdNotFoundException{
		Question q = dao.getQuestion(id);
		if(q!=null) {
			ResponseStructure<Question> rs=new ResponseStructure<Question>();
			rs.setData(q);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data fetched succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
		
	}

	public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestion() throws DataNotFoundException {
		List<Question> li = dao.getAllQuestion();
		if(li!=null) {
			ResponseStructure<List<Question>> rs=new ResponseStructure<List<Question>>();
			rs.setData(li);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data fetched succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<List<Question>>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new DataNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Question>> updateQuestion(Question question) throws IdNotFoundException {
		Question db = dao.updateQuestion(question);
		if(db!=null) {
			ResponseStructure<Question> rs=new ResponseStructure<Question>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data updated succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Question>> deleteQuestion(int id) throws IdNotFoundException {
		Question db = dao.deleteQuestion(id);
		if(db!=null) {
			ResponseStructure<Question> rs=new ResponseStructure<Question>();
			rs.setData(db);
			rs.setDt(LocalDateTime.now());
			rs.setMessage("your data deleted succesfully in our database...!");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Question>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Question>>> getQuestionByCatagory(String catagory) throws DataNotFoundException {
		List<Question> ques = dao.getQuestionByCatagory(catagory);
		try{
			if(ques!=null) {
				ResponseStructure<List<Question>> rs=new ResponseStructure<List<Question>>();
				rs.setData(ques);
				rs.setDt(LocalDateTime.now());
				rs.setMessage("your data fetched succesfully in our database...!");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<List<Question>>>(rs,HttpStatus.ACCEPTED);
			}
			else {
				throw new DataNotFoundException();
			}
		}
		catch(Exception ex) {
			throw new DataNotFoundException();
		}
	}
}
