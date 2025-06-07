package jhomt.com.studytimeapi.Domain.Student;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentService(StudentRepository studentRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentRepository = studentRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListStudent registerStudent(DataRegisterStudent dataRegisterStudent) {
        Student student = new Student(dataRegisterStudent);
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
}
