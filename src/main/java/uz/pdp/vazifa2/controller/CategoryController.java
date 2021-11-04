package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.payload.CategoryDto;
import uz.pdp.vazifa2.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategory() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Integer id) {
        Category category = categoryService.getCategory(id);
        if (category == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        return ResponseEntity.status(200).body(category);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);

        if (apiResponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.editCategory(categoryDto, id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }



}
