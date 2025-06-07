package jhomt.com.studytimeapi.Domain.GlobalConfiguration;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GlobalConfigurationService {

    private final GlobalConfigurationRepository globalConfigurationRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public GlobalConfigurationService(GlobalConfigurationRepository globalConfigurationRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.globalConfigurationRepository = globalConfigurationRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListGlobalConfiguration createGlobalConfiguration(Student student) {
        GlobalConfiguration globalConfiguration = new GlobalConfiguration(student);
        globalConfigurationRepository.save(globalConfiguration);
        return new DataListGlobalConfiguration(globalConfiguration);
    }

    @Transactional
    public DataListGlobalConfiguration updateGlobalConfiguration(DataUpdateGlobalConfiguration dataUpdateGlobalConfiguration) {
        GlobalConfiguration globalConfiguration = validationsIDsGlobalService.findGlobalConfigurationById(dataUpdateGlobalConfiguration.id());

        if (dataUpdateGlobalConfiguration.studentId() != null) {
            validationsIDsGlobalService.findStudentById(dataUpdateGlobalConfiguration.studentId());
        }

        globalConfiguration.update(dataUpdateGlobalConfiguration);
        globalConfiguration = globalConfigurationRepository.save(globalConfiguration);
        return new DataListGlobalConfiguration(globalConfiguration);
    }

    public DataListGlobalConfiguration findConfigurationByStudentId(Integer studentId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        return new DataListGlobalConfiguration(student.getGlobalConfiguration());
    }
}
