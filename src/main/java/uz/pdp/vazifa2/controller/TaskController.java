package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Task;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.payload.TaskDto;
import uz.pdp.vazifa2.payload.UserDto;
import uz.pdp.vazifa2.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Integer id) {
        Task task = taskService.getUser(id);
        if (task == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        return ResponseEntity.status(200).body(task);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addTask(@Valid @RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.addTask(taskDto);

        if (apiResponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTask(@Valid @RequestBody TaskDto taskDto, @PathVariable Integer id) {
        ApiResponse apiResponse = taskService.editTask(taskDto, id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.deleteTask(id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
