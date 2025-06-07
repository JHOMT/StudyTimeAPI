package jhomt.com.studytimeapi.Domain.Forum;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ForumService {

    private final ForumRepository forumRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public ForumService(ForumRepository forumRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.forumRepository = forumRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public Forum registerForum(DataRegisterForum dataRegisterForum) {
        Forum forum = new Forum(dataRegisterForum);
        return forumRepository.save(forum);
    }

    @Transactional
    public Forum updateForum(DataUpdateForum dataUpdateForum) {
        Forum forum = validationsIDsGlobalService.findForumById(dataUpdateForum.id());
        forum.update(dataUpdateForum);
        return forumRepository.save(forum);
    }

    public List<DataListForum> listForums() {
        List<Forum> forums = forumRepository.findAll();
        return forums.stream()
                .map(DataListForum::new)
                .toList();
    }
}
