package com.example.quiz.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.quiz.dto.Question;
import com.example.quiz.exception.IdNotFoundException;
import com.example.quiz.repo.QuestionRepo;
@Repository
public class QuestionDao {
	@Autowired
	private QuestionRepo repo;
	
	public Question saveQuestion(Question question) {
		return repo.save(question);
	}
	
	public Question getQuestion(int id) {
		Optional<Question> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	
	public List<Question> getAllQuestion(){
		return repo.findAll();
	}
	
	public Question updateQuestion(Question question) throws IdNotFoundException {
	    Optional<Question> dbOptional = repo.findById(question.getId());
	    
	    if (dbOptional.isPresent()) {
	        Question db = dbOptional.get();
	        
	        if (question.getQuestiionTitle() != null) {
	            db.setQuestiionTitle(question.getQuestiionTitle());
	        }
	        if (question.getOption1() != null) {
	            db.setOption1(question.getOption1());
	        }
	        if (question.getOption2() != null) {
	            db.setOption2(question.getOption2());
	        }
	        if (question.getOption3() != null) {
	            db.setOption3(question.getOption3());
	        }
	        if (question.getOption4() != null) {
	            db.setOption4(question.getOption4());
	        }
	        if (question.getDifficultyLevel() != null) {
	            db.setDifficultyLevel(question.getDifficultyLevel());
	        }
	        if (question.getRightAnswer() != null) {
	            db.setRightAnswer(question.getRightAnswer());
	        }
	        return repo.save(db);
	    } else {
	        return null;
	    }
	}

	public Question deleteQuestion(int id) {
		Optional<Question> db = repo.findById(id);
		if(db.isPresent()) {
			Question d = db.get();
			repo.deleteById(id);
			return d;
		}
		else {
			return null;
		}
	}
	
	public List<Question> getQuestionByCatagory(String catagory){
		List<Question> db = repo.findQuestionByCatagory(catagory);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}
}
