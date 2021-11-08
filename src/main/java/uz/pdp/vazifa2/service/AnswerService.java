package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Answer;
import uz.pdp.vazifa2.entity.Task;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.payload.AnswerDto;
import uz.pdp.vazifa2.repository.AnswerRepository;
import uz.pdp.vazifa2.repository.TaskRepository;
import uz.pdp.vazifa2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    public ApiResponse addAnswer(AnswerDto answerDto) {

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);

        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());

        answerRepository.save(answer);

        return new ApiResponse("saved", true);

    }

    public ApiResponse editAnswer(AnswerDto answerDto, Integer id) {

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("answer not found", false);

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);

        Answer answer = new Answer();
        answer.setId(id);
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());

        answerRepository.save(answer);

        return new ApiResponse("edited", true);

    }

    public ApiResponse deleteAnswer(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("answer not found", false);

        answerRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
