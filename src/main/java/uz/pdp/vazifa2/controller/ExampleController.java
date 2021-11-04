package uz.pdp.vazifa2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Example;
import uz.pdp.vazifa2.payload.ExampleDto;
import uz.pdp.vazifa2.service.ExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @GetMapping
    public ResponseEntity<List<Example>> getExample() {
        return ResponseEntity.ok(exampleService.getExamples());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExample(@PathVariable Integer id) {
        Example example = exampleService.getExample(id);
        if (example == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        return ResponseEntity.status(200).body(example);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addExample(@Valid @RequestBody ExampleDto exampleDto) {
        ApiResponse apiResponse = exampleService.addExample(exampleDto);

        if (apiResponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @RequestBody ExampleDto exampleDto, @PathVariable Integer id) {
        ApiResponse apiResponse = exampleService.editExample(exampleDto, id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
