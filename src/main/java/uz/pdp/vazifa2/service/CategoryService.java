package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.payload.CategoryDto;
import uz.pdp.vazifa2.repository.CategoryRepository;
import uz.pdp.vazifa2.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);

        boolean byName = categoryRepository.existsByNameAndLanguageId(categoryDto.getName(), categoryDto.getLanguageId());
        if (byName)
            return new ApiResponse("already exist category", false);

        Category category =  new Category();
        category.setName(categoryDto.getName());
        category.setText(categoryDto.getText());
        category.setLanguage(optionalLanguage.get());

        categoryRepository.save(category);

        return new ApiResponse("saved", true);

    }

    public ApiResponse editCategory(CategoryDto categoryDto, Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);

        boolean byName = categoryRepository.existsByNameAndLanguageIdAndIdNot(categoryDto.getName(), categoryDto.getLanguageId(), id);
        if (byName)
            return new ApiResponse("already exist category", false);

        Category category =  new Category();
        category.setId(id);
        category.setName(categoryDto.getName());
        category.setText(categoryDto.getText());
        category.setLanguage(optionalLanguage.get());

        categoryRepository.save(category);

        return new ApiResponse("edited", true);

    }

    public ApiResponse deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);

        categoryRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
