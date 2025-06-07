package jhomt.com.studytimeapi.Domain.StudentTask;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListStudentTask(
        Integer id,
        Integer studentId,
        Integer taskId,
        String completionDate,
        Importance importance,
        Boolean isCompleted,
        Integer pointsAwarded
) {
    public DataListStudentTask(StudentTask studentTask) {
        this(
                studentTask.getId(),
                studentTask.getStudent().getId(),
                studentTask.getTask() != null ? studentTask.getTask().getId() : null,
                DateUtils.formatDate(studentTask.getCompletionDate()),
                studentTask.getImportance(),
                studentTask.getIsCompleted(),
                studentTask.getPointsAwarded()
        );
    }
}
