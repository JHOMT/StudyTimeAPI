package jhomt.com.studytimeapi.Domain.StudentForum;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.Forum.Forum;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentForumService {

    private final StudentForumRepository studentForumRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentForumService(StudentForumRepository studentForumRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentForumRepository = studentForumRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    // Registrar la participación de un estudiante en un foro
    @Transactional
    public DataListStudentForum registerStudentForum(DataRegisterStudentForum dataRegisterStudentForum) {
        Student student = validationsIDsGlobalService.findStudentById(dataRegisterStudentForum.studentId());
        Forum forum = validationsIDsGlobalService.findForumById(dataRegisterStudentForum.forumId());

        StudentForum studentForum = new StudentForum();
        studentForum.setStudent(student);
        studentForum.setForum(forum);

        studentForum = studentForumRepository.save(studentForum);
        return new DataListStudentForum(studentForum);
    }

    @Transactional
    public DataListStudentForum updateStudentForum(DataUpdateStudentForum dataUpdateStudentForum) {
        StudentForum studentForum = studentForumRepository.findById(dataUpdateStudentForum.id())
                .orElseThrow(() -> new RuntimeException("Participación no encontrada"));

        studentForum.setResponse(dataUpdateStudentForum.response());
        studentForum.setPointsAwarded(dataUpdateStudentForum.pointsAwarded());

        if (dataUpdateStudentForum.studentId() != null) {
            Student student = validationsIDsGlobalService.findStudentById(dataUpdateStudentForum.studentId());
            studentForum.setStudent(student);
        }

        if (dataUpdateStudentForum.forumId() != null) {
            Forum forum = validationsIDsGlobalService.findForumById(dataUpdateStudentForum.forumId());
            studentForum.setForum(forum);
        }

        studentForum = studentForumRepository.save(studentForum);
        return new DataListStudentForum(studentForum);
    }

    public List<StudentForum> listStudentForums() {
        return studentForumRepository.findAll();
    }

    public List<DataListStudentForum> listStudentForumsByCourseId(Integer courseId) {
        Course course = validationsIDsGlobalService.findCourseById(courseId);
        List<Forum> forums = course.getForums();
        List<DataListStudentForum> studentForums = new ArrayList<>();
        for (Forum forum : forums) {
            List<StudentForum> studentForumList = studentForumRepository.findByForum(forum);
            for (StudentForum studentForum : studentForumList) {
                studentForums.add(new DataListStudentForum(studentForum));
            }
        }
        return studentForums;
    }

}
