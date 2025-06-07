package jhomt.com.studytimeapi.Domain.Task;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public TaskService(TaskRepository taskRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.taskRepository = taskRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListTask registerTask(DataRegisterTask dataRegisterTask) {
        Unit unit = validationsIDsGlobalService.findUnitById(dataRegisterTask.unitId());

        Task task = new Task(dataRegisterTask);
        task.setUnit(unit);

        task = taskRepository.save(task);
        return new DataListTask(task);
    }

    @Transactional
    public DataListTask updateTask(DataUpdateTask dataUpdateTask) {
        Task task = taskRepository.findById(dataUpdateTask.id())
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        task.update(dataUpdateTask);

        if (dataUpdateTask.unitId() != null) {
            Unit unit = validationsIDsGlobalService.findUnitById(dataUpdateTask.unitId());
            task.setUnit(unit);
        }

        task = taskRepository.save(task);
        return new DataListTask(task);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public List<DataListTask> findTaskByUnitId(Integer unitId) {
        Unit unit = validationsIDsGlobalService.findUnitById(unitId);
        return unit.getTasks()
                .stream()
                .map(DataListTask::new)
                .toList();
    }
}
