package jhomt.com.studytimeapi.Domain.Reward;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.StudentReward.StudentReward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "points_needed", nullable = false)
    private Integer pointsNeeded;

    @OneToMany(mappedBy = "reward", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentReward> studentRewardList;

    public Reward(DataRegisterReward reward) {
        this.name = reward.name();
        this.pointsNeeded = reward.pointsNeeded();
    }

    public void update(DataUpdateReward reward) {
        EntityUpdater.updateIfNotNull(reward.name(), this::setName);
        EntityUpdater.updateIfNotNull(reward.pointsNeeded(), this::setPointsNeeded);
    }
}