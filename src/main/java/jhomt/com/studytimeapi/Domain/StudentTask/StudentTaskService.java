package jhomt.com.studytimeapi.Domain.StudentTask;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import jhomt.com.studytimeapi.Domain.Student.StudentRepository;
import jhomt.com.studytimeapi.Domain.Student.StudentService;
import jhomt.com.studytimeapi.Domain.Task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentTaskService {

    private final StudentTaskRepository studentTaskRepository;
    private final StudentService studentService;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentTaskService(StudentTaskRepository studentTaskRepository, StudentService studentService, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentTaskRepository = studentTaskRepository;
        this.studentService = studentService;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListStudentTask registerStudentTask(DataRegisterStudentTask dataRegisterStudentTask) {
        Student student = validationsIDsGlobalService.findStudentById(dataRegisterStudentTask.studentId());
        Task task = validationsIDsGlobalService.findTaskById(dataRegisterStudentTask.taskId());

        StudentTask studentTask = new StudentTask(dataRegisterStudentTask);
        studentTask.setStudent(student);
        studentTask.setTask(task);

        studentService.sumPoints(student, studentTask.getPointsAwarded());

        studentTask = studentTaskRepository.save(studentTask);
        return new DataListStudentTask(studentTask);
    }

    @Transactional
    public DataListStudentTask updateStudentTask(DataUpdateStudentTask dataUpdateStudentTask) {
        StudentTask studentTask = studentTaskRepository.findById(dataUpdateStudentTask.id())
                .orElseThrow(() -> new RuntimeException("Tarea del estudiante no encontrada"));

        studentTask.update(dataUpdateStudentTask);

        if (dataUpdateStudentTask.studentId() != null) {
            Student student = validationsIDsGlobalService.findStudentById(dataUpdateStudentTask.studentId());
            studentTask.setStudent(student);
        }

        if (dataUpdateStudentTask.taskId() != null) {
            Task task = validationsIDsGlobalService.findTaskById(dataUpdateStudentTask.taskId());
            studentTask.setTask(task);
        }

        studentTask = studentTaskRepository.save(studentTask);
        return new DataListStudentTask(studentTask);
    }

    public List<DataListStudentTask> listStudentTasksId(Integer studentId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        return student.getStudentTasks()
                .stream()
                .map(DataListStudentTask::new)
                .toList();
    }

    public List<DataListStudentTask> listStudentTasksByTaskId(Integer taskId) {
        Task task = validationsIDsGlobalService.findTaskById(taskId);
        return task.getStudentTasks()
                .stream()
                .map(DataListStudentTask::new)
                .toList();
    }
}
