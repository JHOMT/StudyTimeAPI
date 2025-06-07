package jhomt.com.studytimeapi.Domain.CourseType;

public record DataListCourseType(
        Integer id,
        String name,
        String description
) {
    public DataListCourseType(CourseType courseType) {
        this(
                courseType.getId(),
                courseType.getName(),
                courseType.getDescription()
        );
    }
}
