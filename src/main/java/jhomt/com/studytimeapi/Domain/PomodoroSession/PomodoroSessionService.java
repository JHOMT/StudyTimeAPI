package jhomt.com.studytimeapi.Domain.PomodoroSession;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PomodoroSessionService {

    private final PomodoroSessionRepository pomodoroSessionRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public PomodoroSessionService(PomodoroSessionRepository pomodoroSessionRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.pomodoroSessionRepository = pomodoroSessionRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListPomodoroSession registerPomodoroSession(DataRegisterPomodoroSession dataRegisterPomodoroSession) {
        PomodoroSession pomodoroSession = new PomodoroSession(dataRegisterPomodoroSession);
        return new DataListPomodoroSession( pomodoroSessionRepository.save(pomodoroSession));
    }

    @Transactional
    public DataListPomodoroSession updatePomodoroSession(DataUpdatePomodoroSession dataUpdatePomodoroSession) {
        PomodoroSession pomodoroSession = validationsIDsGlobalService.findPomodoroSessionById(dataUpdatePomodoroSession.id());

        if (dataUpdatePomodoroSession.studentId() != null) {
            Student student = validationsIDsGlobalService.findStudentById(dataUpdatePomodoroSession.studentId());
            pomodoroSession.setStudent(student);
        }

        pomodoroSession.update(dataUpdatePomodoroSession);
        return new DataListPomodoroSession( pomodoroSessionRepository.save(pomodoroSession));
    }

    public List<DataListPomodoroSession> listPomodoroSessionsByStudent(Integer studentId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        return student.getPomodoroSessions()
                .stream()
                .map(DataListPomodoroSession::new)
                .toList();
    }

    public DataListPomodoroSession findById(Integer pomodoroSessionId) {
        return new DataListPomodoroSession(validationsIDsGlobalService.findPomodoroSessionById(pomodoroSessionId));
    }
}
