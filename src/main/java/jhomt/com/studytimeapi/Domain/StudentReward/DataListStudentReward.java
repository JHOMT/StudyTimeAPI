package jhomt.com.studytimeapi.Domain.StudentReward;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListStudentReward(
        Integer id,
        Integer studentId,
        Integer rewardId,
        String earnedDate
) {
    public DataListStudentReward(StudentReward studentReward) {
        this(
                studentReward.getId(),
                studentReward.getStudent().getId(),
                studentReward.getReward().getId(),
                DateUtils.formatDate(studentReward.getEarnedDate())
        );
    }
}
