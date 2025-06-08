package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Task.DataListTask;
import jhomt.com.studytimeapi.Domain.Task.DataRegisterTask;
import jhomt.com.studytimeapi.Domain.Task.DataUpdateTask;
import jhomt.com.studytimeapi.Domain.Task.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> registerTask(@RequestBody @Valid DataRegisterTask dataRegisterTask) {
        try {
            DataListTask task = taskService.registerTask(dataRegisterTask);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering task: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody @Valid DataUpdateTask dataUpdateTask) {
        try {
            DataListTask updatedTask = taskService.updateTask(dataUpdateTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating task: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-unit/{unitId}")
    public ResponseEntity<?> findTaskByUnitId(@PathVariable Integer unitId) {
        try {
            List<DataListTask> tasks = taskService.findTaskByUnitId(unitId);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving tasks by unit ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> findTaskById(@PathVariable Integer taskId) {
        try {
            DataListTask task = taskService.findTaskById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving task by ID: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
