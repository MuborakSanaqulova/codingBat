package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.entity.Task;
import uz.pdp.vazifa2.payload.TaskDto;
import uz.pdp.vazifa2.repository.LanguageRepository;
import uz.pdp.vazifa2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LanguageRepository languageRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getUser(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
            return optionalTask.orElse(null);
    }

    public ApiResponse addTask(TaskDto taskDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);

        boolean byName = taskRepository.existsByNameAndLanguageId(taskDto.getName(), taskDto.getLanguageId());
        if (byName)
            return new ApiResponse("already exist task", false);

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());
        task.setHasStar(taskDto.getHasStar());
        task.setLanguage(optionalLanguage.get());

        taskRepository.save(task);

        return new ApiResponse("saved", true);

    }

    public ApiResponse editTask(TaskDto taskDto, Integer id) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);

        boolean byName = taskRepository.existsByNameAndLanguageIdAndIdNot(taskDto.getName(), taskDto.getLanguageId(), id);
        if (byName)
            return new ApiResponse("already exist task", false);

        Task task = new Task();
        task.setId(id);
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());
        task.setHasStar(taskDto.getHasStar());
        task.setLanguage(optionalLanguage.get());
        taskRepository.save(task);

        return new ApiResponse("saved", true);


    }

    public ApiResponse deleteTask(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        taskRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }
}
