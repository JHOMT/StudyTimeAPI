package jhomt.com.studytimeapi.Domain.Task;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListTask(
        Integer id,
        Integer unitId,
        String title,
        String description,
        TaskType typeTask,
        String dueDate,
        Integer points
) {
    public DataListTask(Task task) {
        this(
                task.getId(),
                task.getUnit() != null ? task.getUnit().getId() : null,
                task.getTitle() != null ? task.getTitle() : "",
                task.getDescription(),
                task.getTypeTask(),
                DateUtils.formatDate(task.getDueDate()),
                task.getPoints()
        );
    }
}
