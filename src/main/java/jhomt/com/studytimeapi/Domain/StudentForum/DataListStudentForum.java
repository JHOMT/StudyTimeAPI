package jhomt.com.studytimeapi.Domain.StudentForum;

import jhomt.com.studytimeapi.Domain.Forum.Forum;

public record DataListStudentForum(
        Integer id,
        Integer studentId,
        Integer forumId,
        String response,
        Integer pointsAwarded
) {
    public DataListStudentForum(StudentForum studentForum) {
        this(
                studentForum.getId(),
                studentForum.getStudent().getId(),
                studentForum.getForum().getId(),
                studentForum.getResponse(),
                studentForum.getPointsAwarded()
        );
    }
}
