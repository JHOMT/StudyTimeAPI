package jhomt.com.studytimeapi.Domain.Course;

public record DataListCourse(
        Integer id,
        String title,
        String description,
        String typeName,
        Boolean status
) {
    public DataListCourse(Course cours) {
        this(
                cours.getId(),
                cours.getTitle(),
                cours.getDescription(),
                cours.getType().getName(),
                cours.getStatus()
        );
    }
}
