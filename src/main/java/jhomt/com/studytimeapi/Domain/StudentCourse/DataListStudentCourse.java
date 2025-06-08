package jhomt.com.studytimeapi.Domain.StudentCourse;

public record DataListStudentCourse(
        Integer studentId,
        String StudentName,
        Integer courseId,
        String courseTitle,
        String courseTypeName
) {
    public DataListStudentCourse(StudentCourse studentCourse) {
        this(
                studentCourse.getStudent().getId(),
                studentCourse.getStudent().getName(),
                studentCourse.getCourse().getId(),
                studentCourse.getCourse().getTitle(),
                studentCourse.getCourse().getType().getName()
        );
    }
}
