package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.StudentTask.DataListStudentTask;
import jhomt.com.studytimeapi.Domain.StudentTask.DataRegisterStudentTask;
import jhomt.com.studytimeapi.Domain.StudentTask.DataUpdateStudentTask;
import jhomt.com.studytimeapi.Domain.StudentTask.StudentTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-tasks")
public class StudentTaskController {

    private final StudentTaskService studentTaskService;

    public StudentTaskController(StudentTaskService studentTaskService) {
        this.studentTaskService = studentTaskService;
    }

    @PostMapping
    public ResponseEntity<?> registerStudentTask(@Valid @RequestBody DataRegisterStudentTask dataRegisterStudentTask) {
        try {
            DataListStudentTask studentTask = studentTaskService.registerStudentTask(dataRegisterStudentTask);
            return new ResponseEntity<>(studentTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering student task: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStudentTask(@Valid @RequestBody DataUpdateStudentTask dataUpdateStudentTask) {
        try {
            DataListStudentTask updatedStudentTask = studentTaskService.updateStudentTask(dataUpdateStudentTask);
            return new ResponseEntity<>(updatedStudentTask, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating student task: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-student-id/{studentId}")
    public ResponseEntity<?> listStudentTasksId(@PathVariable Integer studentId) {
        try {
            List<DataListStudentTask> studentTasks = studentTaskService.listStudentTasksId(studentId);
            return new ResponseEntity<>(studentTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student tasks by task ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-task-id/{taskId}")
    public ResponseEntity<?> listStudentTasksByTaskId(@PathVariable Integer taskId) {
        try {
            List<DataListStudentTask> studentTasks = studentTaskService.listStudentTasksByTaskId(taskId);
            return new ResponseEntity<>(studentTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student tasks: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
