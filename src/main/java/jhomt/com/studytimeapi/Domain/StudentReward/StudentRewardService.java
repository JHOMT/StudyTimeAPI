package jhomt.com.studytimeapi.Domain.StudentReward;

import jhomt.com.studytimeapi.Domain.Reward.Reward;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentRewardService {

    private final StudentRewardRepository studentRewardRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentRewardService(StudentRewardRepository studentRewardRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentRewardRepository = studentRewardRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListStudentReward registerStudentReward(DataRegisterStudentReward dataRegisterStudentReward) {
        Student student = validationsIDsGlobalService.findStudentById(dataRegisterStudentReward.studentId());
        Reward reward = validationsIDsGlobalService.findRewardById(dataRegisterStudentReward.rewardId());

        StudentReward studentReward = new StudentReward(dataRegisterStudentReward);
        studentReward.setStudent(student);
        studentReward.setReward(reward);

        studentReward = studentRewardRepository.save(studentReward);
        return new DataListStudentReward(studentReward);
    }

    @Transactional
    public DataListStudentReward updateStudentReward(DataUpdateStudentReward dataUpdateStudentReward) {
        StudentReward studentReward = studentRewardRepository.findById(dataUpdateStudentReward.id())
                .orElseThrow(() -> new RuntimeException("Recompensa del estudiante no encontrada"));

        studentReward.update(dataUpdateStudentReward);

        if (dataUpdateStudentReward.studentId() != null) {
            Student student = validationsIDsGlobalService.findStudentById(dataUpdateStudentReward.studentId());
            studentReward.setStudent(student);
        }

        if (dataUpdateStudentReward.rewardId() != null) {
            Reward reward = validationsIDsGlobalService.findRewardById(dataUpdateStudentReward.rewardId());
            studentReward.setReward(reward);
        }

        studentReward = studentRewardRepository.save(studentReward);
        return new DataListStudentReward(studentReward);
    }

    public List<StudentReward> listStudentRewards() {
        return studentRewardRepository.findAll();
    }

    public List<DataListStudentReward> findStudentRewardById(Integer studentRewardId) {
        return studentRewardRepository.findByStudentId(studentRewardId)
                .stream()
                .map(DataListStudentReward::new)
                .toList();
    }
}
