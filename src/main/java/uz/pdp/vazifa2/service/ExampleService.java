package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Example;
import uz.pdp.vazifa2.entity.Task;
import uz.pdp.vazifa2.payload.ExampleDto;
import uz.pdp.vazifa2.repository.ExampleRepository;
import uz.pdp.vazifa2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {


    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<Example> getExamples() {
        return exampleRepository.findAll();
    }

    public Example getExample(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse addExample(ExampleDto exampleDto) {

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        boolean byText = exampleRepository.existsByText(exampleDto.getText());
        if (byText)
            return new ApiResponse("already exist example", false);

        Example example = new Example();
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());

        exampleRepository.save(example);

        return new ApiResponse("saved", true);

    }

    public ApiResponse editExample(ExampleDto exampleDto, Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("example not found", false);

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        boolean byTextAndIdNot = exampleRepository.existsByTextAndIdNot(exampleDto.getText(), id);
        if (byTextAndIdNot)
            return new ApiResponse("already exist example", false);

        Example example = new Example();
        example.setId(id);
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());

        exampleRepository.save(example);

        return new ApiResponse("edited", true);

    }

    public ApiResponse deleteExample(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("example not found", false);

        exampleRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }
}
