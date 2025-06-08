package jhomt.com.studytimeapi.Domain.Student;

import jhomt.com.studytimeapi.Domain.GlobalConfiguration.GlobalConfigurationService;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;
    private final GlobalConfigurationService  globalConfigurationService;

    public StudentService(StudentRepository studentRepository, ValidationsIDsGlobalService validationsIDsGlobalService, GlobalConfigurationService globalConfigurationService) {
        this.studentRepository = studentRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
        this.globalConfigurationService = globalConfigurationService;
    }

    @Transactional
    public DataListStudent registerStudentAndCreateConfiguration(DataRegisterStudent dataRegisterStudent) {
        Student student = new Student(dataRegisterStudent);

        globalConfigurationService.createGlobalConfiguration(student);

        student = studentRepository.save(student);
        return new DataListStudent(student);
    }

    @Transactional
    public DataListStudent updateStudent(DataUpdateStudent dataUpdateStudent) {
        Student student = validationsIDsGlobalService.findStudentById(dataUpdateStudent.id());
        student.update(dataUpdateStudent);
        student = studentRepository.save(student);
        return new DataListStudent(student);
    }

    public List<DataListStudent> listStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(DataListStudent::new)
                .collect(Collectors.toList());
    }

    public List<DataListStudent> listStudentsTop(Integer amount) {
        List<Student> students = studentRepository.findDistinctTopByTotalPoints(PageRequest.of(0, amount));
        return students.stream()
                .map(DataListStudent::new)
                .collect(Collectors.toList());
    }

    public void sumPoints(Student student, Integer pointsAwarded) {
        student.sumPoints(pointsAwarded);
        studentRepository.save(student);
    }

    public DataListStudent authLogin(DataLoginStudent dataLoginStudent){
        Optional<Student> student = studentRepository.login(dataLoginStudent.email(), dataLoginStudent.password());
        if (student.isPresent()) {
            return new DataListStudent(student.get());
        }
        throw new RuntimeException("Invalid email or password");
    }
}
