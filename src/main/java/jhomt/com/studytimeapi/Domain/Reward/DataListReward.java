package jhomt.com.studytimeapi.Domain.Reward;

import jhomt.com.studytimeapi.Domain.StudentReward.StudentReward;

public record DataListReward(
        Integer id,
        String name,
        Integer pointsNeeded
) {
    public DataListReward(Reward reward) {
        this(
                reward.getId(),
                reward.getName(),
                reward.getPointsNeeded()
        );
    }
}