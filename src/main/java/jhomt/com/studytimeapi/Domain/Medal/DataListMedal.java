package jhomt.com.studytimeapi.Domain.Medal;

public record DataListMedal(
        Integer id,
        String name,
        String description,
        String criteria
) {
    public DataListMedal(Medal medal) {
        this(
                medal.getId(),
                medal.getName(),
                medal.getDescription(),
                medal.getCriteria()
        );
    }
}
