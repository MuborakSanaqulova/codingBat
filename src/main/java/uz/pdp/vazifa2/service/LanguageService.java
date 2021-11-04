package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.payload.LanguageDto;
import uz.pdp.vazifa2.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    
    @Autowired
    LanguageRepository languageRepository;

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguage(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);

    }

    public ApiResponse addLanguage(LanguageDto languageDto) {
        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        
        return new ApiResponse("SAVED", true);
    }

    public ApiResponse editLanguage(LanguageDto languageDto, Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);

        Language language = new Language();
        language.setId(id);
        language.setName(languageDto.getName());
        languageRepository.save(language);

        return new ApiResponse("language saved", true);

    }

    public ApiResponse deleteLanguage(Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);

        languageRepository.deleteById(id);
        return new ApiResponse("language deleted", true);

    }
}
