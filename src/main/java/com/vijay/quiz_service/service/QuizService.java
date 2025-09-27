package com.vijay.quiz_service.service;

import com.vijay.quiz_service.dao.QuizDao;
import com.vijay.quiz_service.feign.QuizInterface;
import com.vijay.quiz_service.model.QuestionWrapper;
import com.vijay.quiz_service.model.Quiz;
import com.vijay.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;
    // @Autowired
    // QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questionIds= quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        System.out.println("i am here with quiz id = "+id);
        List<Integer> questionIds = quizDao.findById(id).get().getQuestionIds();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        questionsForUser= quizInterface.getQuestions(questionIds).getBody();
        System.out.println("called quizInterface");
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        int right = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
