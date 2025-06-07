package jhomt.com.studytimeapi.Domain.Unit;

public record DataListUnit(
        Integer id,
        Integer courseId,
        String title,
        String description,
        Boolean completed
) {
    public DataListUnit(Unit unit) {
        this(
                unit.getId(),
                unit.getCourse() != null ? unit.getCourse().getId() : null,
                unit.getTitle(),
                unit.getDescription(),
                unit.getCompleted()
        );
    }
}