package jhomt.com.studytimeapi.Domain.StudentForum;

import jhomt.com.studytimeapi.Domain.Forum.Forum;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentForumService {

    private final StudentForumRepository studentForumRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentForumService(StudentForumRepository studentForumRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentForumRepository = studentForumRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListStudentForum registerStudentForum(DataRegisterStudentForum dataRegisterStudentForum) {
        Student student = validationsIDsGlobalService.findStudentById(dataRegisterStudentForum.studentId());
        Forum forum = validationsIDsGlobalService.findForumById(dataRegisterStudentForum.forumId());

        StudentForum studentForum = new StudentForum(dataRegisterStudentForum);
        studentForum.setStudent(student);
        studentForum.setForum(forum);

        studentForum = studentForumRepository.save(studentForum);
        return new DataListStudentForum(studentForum);
    }

    @Transactional
    public DataListStudentForum updateStudentForum(DataUpdateStudentForum dataUpdateStudentForum) {
        StudentForum studentForum = validationsIDsGlobalService.findStudentForumRepositoryById(dataUpdateStudentForum.id());

        studentForum.update(dataUpdateStudentForum);

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

    public List<DataListStudentForum> findStudentForumById(Integer forumId) {
        Forum forum = validationsIDsGlobalService.findForumById(forumId);
        return forum.getStudentForum()
                .stream()
                .map(DataListStudentForum::new)
                .toList();
    }

}
