package jhomt.com.studytimeapi.Domain.CourseType;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
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
    public DataListCourseType registerCourseType(DataRegisterCourseType dataRegisterCourseType) {
        CourseType courseType = new CourseType(dataRegisterCourseType);
        courseType = courseTypeRepository.save(courseType);
        return new DataListCourseType(courseType);
    }

    @Transactional
    public DataListCourseType updateCourseType(DataUpdateCourseType dataUpdateCourseType) {
        CourseType courseType = validationsIDsGlobalService.findCourseTypeById(dataUpdateCourseType.id());
        courseType.update(dataUpdateCourseType);
        courseType = courseTypeRepository.save(courseType);
        return new DataListCourseType(courseType);
    }

    public List<DataListCourseType> listCourseTypes() {
        List<CourseType> courseTypes = courseTypeRepository.findAll();
        return courseTypes.stream()
                .map(DataListCourseType::new)
                .toList();
    }

    public DataListCourseType findById(int id) {
        return new DataListCourseType(validationsIDsGlobalService.findCourseTypeById(id));
    }
}
