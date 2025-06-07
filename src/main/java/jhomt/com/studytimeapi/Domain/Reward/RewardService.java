package jhomt.com.studytimeapi.Domain.Reward;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public RewardService(RewardRepository rewardRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.rewardRepository = rewardRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListReward registerReward(DataRegisterReward dataRegisterReward) {
        Reward reward = new Reward(dataRegisterReward);
        return new DataListReward(rewardRepository.save(reward));
    }

    @Transactional
    public DataListReward updateReward(DataUpdateReward dataUpdateReward) {
        Reward reward = validationsIDsGlobalService.findRewardById(dataUpdateReward.id());
        reward.update(dataUpdateReward);
        return new DataListReward(rewardRepository.save(reward));
    }

    public List<DataListReward> listRewards() {
        List<Reward> rewards = rewardRepository.findAll();
        return rewards.stream()
                .map(DataListReward::new)
                .toList();
    }
}
