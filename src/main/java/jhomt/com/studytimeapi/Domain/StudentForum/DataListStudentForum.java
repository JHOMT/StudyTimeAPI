package jhomt.com.studytimeapi.Domain.StudentForum;

public record DataListStudentForum(
        Integer id,
        Integer studentId,
        String studentName,
        Integer forumId,
        String response,
        Integer pointsAwarded
) {
    public DataListStudentForum(StudentForum studentForum) {
        this(
                studentForum.getId(),
                studentForum.getStudent().getId(),
                studentForum.getStudent().getName(),
                studentForum.getForum().getId(),
                studentForum.getResponse(),
                studentForum.getPointsAwarded()
        );
    }
}
