package com.vijay.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.vijay.quiz_service.model.QuestionWrapper;
import com.vijay.quiz_service.model.Response;

@FeignClient(name="question-service")
public interface QuizInterface {
    @PostMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numberOfQuestions);
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestions(@RequestBody List<Integer> qIds);
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
