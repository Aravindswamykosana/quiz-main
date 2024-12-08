package com.example.quiz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.quiz.dto.Question;
import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.QuizAnswerResponse;
import com.example.quiz.dto.QuizWrapper;
import com.example.quiz.repo.QuestionRepo;
import com.example.quiz.repo.QuizRepo;

@Repository
public class QuizDao {
	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuestionRepo questionRepo;
	
	public Quiz createQuiz(String catagory,String title){
		List<Question> questions = questionRepo.findRandomQuestionsByCatagory(catagory);
		if(questions!=null) {
			Quiz q=new Quiz();
			q.setTitle(title);
			q.setQuestions(questions);
			return quizRepo.save(q);
		}
		else {
			return null;
		}
	}

	public List<QuizWrapper> getQuiz(int id) {
		Optional<Quiz> db = quizRepo.findById(id);
		if(db.isPresent()) {
			List<Question> qdata = db.get().getQuestions();
			if(qdata!=null) {
				List<QuizWrapper> qw=new ArrayList<QuizWrapper>();
				
				for(Question q:qdata) {
					QuizWrapper qq=new QuizWrapper(q.getId(),q.getQuestiionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
					qw.add(qq);
				}
				return qw;
			}
			else {
				return null;
			}
			
		}
		else {
			return null;
		}	
	}
	
	public Quiz deleteQuiz(int id){
		Optional<Quiz> db = quizRepo.findById(id);
		if(db.isPresent()) {
			 Quiz data = db.get();
			 quizRepo.deleteById(id);
			 return data;
		}
		return null;
	}

	public int getQuizAnswer(Integer id, List<QuizAnswerResponse> response) {
		Quiz db = quizRepo.findById(id).get();
		if(db!=null){
			List<Question> qu = db.getQuestions();
			if(qu!=null) {
				int c=0;
				int i=0;
				for(QuizAnswerResponse r:response) {
					if(r.getResponse().equals(qu.get(i).getRightAnswer())) {
						c++;
					}
					i++;
				}
				return c;
			}
			else {
				return 0;
			}
			
		}
		else {
			return 0;
		}
	}
}
