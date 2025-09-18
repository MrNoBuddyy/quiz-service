package com.vijay.quiz_service.service;

import com.vijay.quiz_service.dao.QuizDao;
import com.vijay.quiz_service.feign.QuizInterface;
import com.vijay.quiz_service.model.QuestionWrapper;
import com.vijay.quiz_service.model.Quiz;
import com.vijay.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        // Optional<Quiz> quiz = quizDao.findById(id);
        // List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        // for(Question q : questionsFromDB){
        //     QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
        //     questionsForUser.add(qw);
        // }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        // Quiz quiz = quizDao.findById(id).get();
        // List<Question> questions = quiz.getQuestions();
        int right = 0;
        // int i = 0;
        // for(Response response : responses){
        //     if(response.getResponse().equals(questions.get(i).getRightAnswer()))
        //         right++;

        //     i++;
        // }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
