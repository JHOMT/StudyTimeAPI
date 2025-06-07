package jhomt.com.studytimeapi.Domain.StudentReward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRewardRepository extends JpaRepository<StudentReward, Integer> {
    List<StudentReward> findByStudentId(Integer studentRewardId);
}
