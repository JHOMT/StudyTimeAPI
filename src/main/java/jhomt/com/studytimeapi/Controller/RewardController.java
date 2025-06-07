package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Reward.DataListReward;
import jhomt.com.studytimeapi.Domain.Reward.DataRegisterReward;
import jhomt.com.studytimeapi.Domain.Reward.DataUpdateReward;
import jhomt.com.studytimeapi.Domain.Reward.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping
    public ResponseEntity<?> registerReward(@Valid @RequestBody DataRegisterReward dataRegisterReward) {
        try {
            DataListReward reward = rewardService.registerReward(dataRegisterReward);
            return new ResponseEntity<>(reward, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering reward: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateReward(@Valid @RequestBody DataUpdateReward dataUpdateReward) {
        try {
            DataListReward updatedReward = rewardService.updateReward(dataUpdateReward);
            return new ResponseEntity<>(updatedReward, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating reward: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listRewards() {
        try {
            List<DataListReward> rewards = rewardService.listRewards();
            return new ResponseEntity<>(rewards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving rewards: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
