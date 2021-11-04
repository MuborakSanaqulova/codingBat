package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.payload.LanguageDto;
import uz.pdp.vazifa2.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getLanguages() {
        return ResponseEntity.ok(languageService.getLanguages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguage(@PathVariable Integer id) {
        Language language = languageService.getLanguage(id);
        if (language == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        return ResponseEntity.status(200).body(language);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.addLanguage(languageDto);

        if (apiResponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@Valid @RequestBody LanguageDto languageDto, @PathVariable Integer id) {
        ApiResponse apiResponse = languageService.editLanguage(languageDto, id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
}
