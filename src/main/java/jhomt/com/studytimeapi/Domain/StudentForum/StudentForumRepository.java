package jhomt.com.studytimeapi.Domain.StudentForum;

import jhomt.com.studytimeapi.Domain.Forum.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentForumRepository extends JpaRepository<StudentForum, Integer> {
    List<StudentForum> findByForum(Forum forum);
}
