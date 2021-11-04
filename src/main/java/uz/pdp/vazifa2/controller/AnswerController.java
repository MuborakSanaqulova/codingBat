package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Answer;
import uz.pdp.vazifa2.entity.Example;
import uz.pdp.vazifa2.payload.AnswerDto;
import uz.pdp.vazifa2.payload.ExampleDto;
import uz.pdp.vazifa2.service.AnswerService;
import uz.pdp.vazifa2.service.ExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {


    @Autowired
    AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers() {
        return ResponseEntity.ok(answerService.getAnswers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable Integer id) {
        Answer answer = answerService.getAnswer(id);
        if (answer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        return ResponseEntity.status(200).body(answer);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addAnswer(@Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.addAnswer(answerDto);

        if (apiResponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@Valid @RequestBody AnswerDto answerDto, @PathVariable Integer id) {
        ApiResponse apiResponse = answerService.editAnswer(answerDto, id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }


}
