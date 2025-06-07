package jhomt.com.studytimeapi.Domain.Unit;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public UnitService(UnitRepository unitRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.unitRepository = unitRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListUnit registerUnit(DataRegisterUnit dataRegisterUnit) {
        Course course = validationsIDsGlobalService.findCourseById(dataRegisterUnit.courseId());

        Unit unit = new Unit(dataRegisterUnit);
        unit.setCourse(course);

        unit = unitRepository.save(unit);
        return new DataListUnit(unit);
    }

    @Transactional
    public DataListUnit updateUnit(DataUpdateUnit dataUpdateUnit) {
        Unit unit = unitRepository.findById(dataUpdateUnit.id())
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));

        unit.update(dataUpdateUnit);

        if (dataUpdateUnit.courseId() != null) {
            Course course = validationsIDsGlobalService.findCourseById(dataUpdateUnit.courseId());
            unit.setCourse(course);
        }

        unit = unitRepository.save(unit);
        return new DataListUnit(unit);
    }

    public List<DataListUnit> listUnitsByCourseId(Integer courseId) {
        Course course = validationsIDsGlobalService.findCourseById(courseId);
        return course.getUnits()
                .stream()
                .map(DataListUnit::new)
                .toList();
    }
}
