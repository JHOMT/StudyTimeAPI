package jhomt.com.studytimeapi.Domain.Student;

public record DataListStudent(
        Integer id,
        String name,
        String email,
        Integer totalPoints
) {
    public DataListStudent(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getTotalPoints()
        );
    }
}