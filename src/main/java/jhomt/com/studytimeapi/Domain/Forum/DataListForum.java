package jhomt.com.studytimeapi.Domain.Forum;

public record DataListForum(
        Integer id,
        String title,
        String description,
        String courseTitle
) {
    public DataListForum(Forum forum) {
        this(
                forum.getId(),
                forum.getTitle(),
                forum.getDescription(),
                forum.getCourse().getTitle()
        );
    }
}
