package jhomt.com.studytimeapi.Domain.ServiceGlobal;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.Course.CourseRepository;
import jhomt.com.studytimeapi.Domain.CourseType.CourseType;
import jhomt.com.studytimeapi.Domain.CourseType.CourseTypeRepository;
import jhomt.com.studytimeapi.Domain.Forum.Forum;
import jhomt.com.studytimeapi.Domain.Forum.ForumRepository;
import jhomt.com.studytimeapi.Domain.GlobalConfiguration.GlobalConfiguration;
import jhomt.com.studytimeapi.Domain.GlobalConfiguration.GlobalConfigurationRepository;
import jhomt.com.studytimeapi.Domain.Medal.Medal;
import jhomt.com.studytimeapi.Domain.Medal.MedalRepository;
import jhomt.com.studytimeapi.Domain.PomodoroSession.PomodoroSession;
import jhomt.com.studytimeapi.Domain.PomodoroSession.PomodoroSessionRepository;
import jhomt.com.studytimeapi.Domain.Reward.Reward;
import jhomt.com.studytimeapi.Domain.Reward.RewardRepository;
import jhomt.com.studytimeapi.Domain.Student.Student;
import jhomt.com.studytimeapi.Domain.Student.StudentRepository;
import jhomt.com.studytimeapi.Domain.StudentForum.StudentForum;
import jhomt.com.studytimeapi.Domain.StudentForum.StudentForumRepository;
import jhomt.com.studytimeapi.Domain.StudentMedal.StudentMedal;
import jhomt.com.studytimeapi.Domain.StudentMedal.StudentMedalRepository;
import jhomt.com.studytimeapi.Domain.Task.Task;
import jhomt.com.studytimeapi.Domain.Task.TaskRepository;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import jhomt.com.studytimeapi.Domain.Unit.UnitRepository;
import jhomt.com.studytimeapi.Domain.UnitMaterial.UnitMaterial;
import jhomt.com.studytimeapi.Domain.UnitMaterial.UnitMaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationsIDsGlobalService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final GlobalConfigurationRepository globalConfigurationRepository;
    private final RewardRepository rewardRepository;
    private final ForumRepository forumRepository;
    private final MedalRepository medalRepository;
    private final TaskRepository taskRepository;
    private final UnitRepository unitRepository;
    private final CourseTypeRepository courseTypeRepository;
    private final PomodoroSessionRepository pomodoroSessionRepository;
    private final UnitMaterialRepository unitMaterialRepository;
    private final StudentForumRepository studentForumRepository;
    private final StudentMedalRepository studentMedalRepository;

    public ValidationsIDsGlobalService(StudentRepository studentRepository, CourseRepository courseRepository, GlobalConfigurationRepository globalConfigurationRepository, RewardRepository rewardRepository, ForumRepository forumRepository, MedalRepository medalRepository, TaskRepository taskRepository, UnitRepository unitRepository, CourseTypeRepository courseTypeRepository, PomodoroSessionRepository pomodoroSessionRepository, UnitMaterialRepository unitMaterialRepository1, StudentForumRepository studentForumRepository, StudentMedalRepository studentMedalRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.globalConfigurationRepository = globalConfigurationRepository;
        this.rewardRepository = rewardRepository;
        this.forumRepository = forumRepository;
        this.medalRepository = medalRepository;
        this.taskRepository = taskRepository;
        this.unitRepository = unitRepository;
        this.courseTypeRepository = courseTypeRepository;
        this.pomodoroSessionRepository = pomodoroSessionRepository;
        this.unitMaterialRepository = unitMaterialRepository1;
        this.studentForumRepository = studentForumRepository;
        this.studentMedalRepository = studentMedalRepository;
    }

    public Student findStudentById(Integer studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("No se encontro el estudiante con ID: " + studentId));
    }

    public Course findCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("No se encontro el curso con ID: " + courseId));
    }

    public GlobalConfiguration findGlobalConfigurationById(Integer globalConfigurationId) {
        return globalConfigurationRepository.findById(globalConfigurationId)
                .orElseThrow(() -> new RuntimeException("No se encontro la configuracion global con ID: " + globalConfigurationId));
    }

    public Reward findRewardById(Integer rewardId) {
        return rewardRepository.findById(rewardId)
                .orElseThrow(() -> new RuntimeException("No se encontro la recompensa con ID: " + rewardId));
    }

    public Forum findForumById(Integer forumId) {
        return forumRepository.findById(forumId)
                .orElseThrow(() -> new RuntimeException("No se encontro el foro con ID: " + forumId));
    }

    public Medal findMedalById(Integer medalId) {
        return medalRepository.findById(medalId)
                .orElseThrow(() -> new RuntimeException("No se encontro la medalla con ID: " + medalId));
    }

    public Task findTaskById(Integer taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("No se encontro la tarea con ID: " + taskId));
    }

    public Unit findUnitById(Integer unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("No se encontro la unidad con ID: " + unitId));
    }

    public CourseType findCourseTypeById(Integer courseTypeId) {
        return courseTypeRepository.findById(courseTypeId)
                .orElseThrow(() -> new RuntimeException("No se encontro el tipo de curso con ID: " + courseTypeId));
    }

    public PomodoroSession findPomodoroSessionById(Integer pomodoroSessionId) {
        return pomodoroSessionRepository.findById(pomodoroSessionId)
                .orElseThrow(() -> new RuntimeException("No se encontro la sesion de pomodoro con el ID: " + pomodoroSessionId));
    }

    public UnitMaterial findUnitMaterialById(Integer unitMaterialId) {
        return unitMaterialRepository.findById(unitMaterialId)
                .orElseThrow(() -> new RuntimeException("No se encontro el material de la unidad con ID: " + unitMaterialId));
    }

    public StudentForum findStudentForumRepositoryById(Integer studentForumId) {
        return studentForumRepository.findById(studentForumId)
                .orElseThrow(() -> new RuntimeException("No se encontro un estudiante relacionado al foro con el ID: " + studentForumId ));
    }

    public StudentMedal findStudentMedalById( Integer studentMedalId) {
        return studentMedalRepository.findById(studentMedalId)
                .orElseThrow(() -> new RuntimeException("No se encontro el medal con ID: " + studentMedalId));
    }
}
