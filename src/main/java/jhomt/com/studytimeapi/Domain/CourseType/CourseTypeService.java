package jhomt.com.studytimeapi.Domain.CourseType;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseTypeService {

    private final CourseTypeRepository courseTypeRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public CourseTypeService(CourseTypeRepository courseTypeRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.courseTypeRepository = courseTypeRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public CourseType registerCourseType(DataRegisterCourseType dataRegisterCourseType) {
        CourseType courseType = new CourseType(dataRegisterCourseType);
        return courseTypeRepository.save(courseType);
    }

    @Transactional
    public CourseType updateCourseType(DataUpdateCourseType dataUpdateCourseType) {
        CourseType courseType = validationsIDsGlobalService.findCourseTypeById(dataUpdateCourseType.id());
        courseType.update(dataUpdateCourseType);
        return courseTypeRepository.save(courseType);
    }

    public List<DataListCourseType> listCourseTypes() {
        List<CourseType> courseTypes = courseTypeRepository.findAll();
        return courseTypes.stream()
                .map(DataListCourseType::new)
                .toList();
    }
}
