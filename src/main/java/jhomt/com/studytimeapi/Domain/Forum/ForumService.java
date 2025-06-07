package jhomt.com.studytimeapi.Domain.Forum;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForumService {

    private final ForumRepository forumRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public ForumService(ForumRepository forumRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.forumRepository = forumRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListForum registerForum(DataRegisterForum dataRegisterForum) {
        Course course = validationsIDsGlobalService.findCourseById(dataRegisterForum.courseId());

        Forum forum = new Forum(dataRegisterForum);
        forum.setCourse(course);
        forum = forumRepository.save(forum);
        return new DataListForum(forum);
    }

    @Transactional
    public DataListForum updateForum(DataUpdateForum dataUpdateForum) {
        Forum forum = validationsIDsGlobalService.findForumById(dataUpdateForum.id());
        forum.update(dataUpdateForum);
        if (dataUpdateForum.courseId() != null) {
            Course course = validationsIDsGlobalService.findCourseById(dataUpdateForum.courseId());
            forum.setCourse(course);
        }
        forum = forumRepository.save(forum);
        return new DataListForum(forum);
    }

    public List<DataListForum> listForumsByCourseId(Integer courseId) {
        Course course = validationsIDsGlobalService.findCourseById(courseId);
        return course.getForums()
                .stream()
                .map(DataListForum::new)
                .collect(Collectors.toList());
    }
}
