package jhomt.com.studytimeapi.Domain.StudentMedal;

import jhomt.com.studytimeapi.Domain.Medal.Medal;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentMedalService {

    private final StudentMedalRepository studentMedalRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentMedalService(StudentMedalRepository studentMedalRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentMedalRepository = studentMedalRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListStudentMedal registerStudentMedal(DataRegisterStudentMedal dataRegisterStudentMedal) {
        Student student = validationsIDsGlobalService.findStudentById(dataRegisterStudentMedal.studentId());
        Medal medal = validationsIDsGlobalService.findMedalById(dataRegisterStudentMedal.medalId());
        Unit unit = validationsIDsGlobalService.findUnitById(dataRegisterStudentMedal.unitId());

        StudentMedal studentMedal = new StudentMedal(dataRegisterStudentMedal);
        studentMedal.setStudent(student);
        studentMedal.setMedal(medal);
        studentMedal.setUnit(unit);

        studentMedal = studentMedalRepository.save(studentMedal);
        return new DataListStudentMedal(studentMedal);
    }

    @Transactional
    public DataListStudentMedal updateStudentMedal(DataUpdateStudentMedal dataUpdateStudentMedal) {
        StudentMedal studentMedal = validationsIDsGlobalService.findStudentMedalById(dataUpdateStudentMedal.id());

        studentMedal.update(dataUpdateStudentMedal);

        if (dataUpdateStudentMedal.unitId() != null) {
            Unit unit = validationsIDsGlobalService.findUnitById(dataUpdateStudentMedal.unitId());
            studentMedal.setUnit(unit);
        }

        if (dataUpdateStudentMedal.studentId() != null) {
            Student student = validationsIDsGlobalService.findStudentById(dataUpdateStudentMedal.studentId());
            studentMedal.setStudent(student);
        }

        if (dataUpdateStudentMedal.medalId() != null) {
            Medal medal = validationsIDsGlobalService.findMedalById(dataUpdateStudentMedal.medalId());
            studentMedal.setMedal(medal);
        }

        studentMedal = studentMedalRepository.save(studentMedal);
        return new DataListStudentMedal(studentMedal);
    }

    public List<DataListStudentMedal> listStudentMedals() {
        return studentMedalRepository.findAll()
                .stream()
                .map(DataListStudentMedal::new)
                .toList();
    }
    public List<DataListStudentMedal> listStudentMedalsByStudentId(Integer studentId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        return student.getMedals()
                .stream()
                .map(DataListStudentMedal::new)
                .toList();
    }
}
