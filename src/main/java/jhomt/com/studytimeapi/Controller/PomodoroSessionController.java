package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.PomodoroSession.DataListPomodoroSession;
import jhomt.com.studytimeapi.Domain.PomodoroSession.DataRegisterPomodoroSession;
import jhomt.com.studytimeapi.Domain.PomodoroSession.DataUpdatePomodoroSession;
import jhomt.com.studytimeapi.Domain.PomodoroSession.PomodoroSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pomodoro-sessions")
public class PomodoroSessionController {

    private final PomodoroSessionService pomodoroSessionService;

    public PomodoroSessionController(PomodoroSessionService pomodoroSessionService) {
        this.pomodoroSessionService = pomodoroSessionService;
    }

    @PostMapping
    public ResponseEntity<?> registerPomodoroSession(@RequestBody @Valid DataRegisterPomodoroSession dataRegisterPomodoroSession) {
        try {
            DataListPomodoroSession pomodoroSession = pomodoroSessionService.registerPomodoroSession(dataRegisterPomodoroSession);
            return new ResponseEntity<>(pomodoroSession, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering Pomodoro session: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePomodoroSession(@RequestBody @Valid DataUpdatePomodoroSession dataUpdatePomodoroSession) {
        try {
            DataListPomodoroSession updatedPomodoroSession = pomodoroSessionService.updatePomodoroSession(dataUpdatePomodoroSession);
            return new ResponseEntity<>(updatedPomodoroSession, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating Pomodoro session: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<?> listPomodoroSessionsByStudent(@PathVariable Integer studentId) {
        try {
            List<DataListPomodoroSession> pomodoroSessions = pomodoroSessionService.listPomodoroSessionsByStudent(studentId);
            return new ResponseEntity<>(pomodoroSessions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving Pomodoro sessions: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{pomodoroSessionId}")
    public ResponseEntity<?> findPomodoroSessionById(@PathVariable Integer pomodoroSessionId) {
        try {
            DataListPomodoroSession pomodoroSession = pomodoroSessionService.findById(pomodoroSessionId);
            return new ResponseEntity<>(pomodoroSession, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving Pomodoro session: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
