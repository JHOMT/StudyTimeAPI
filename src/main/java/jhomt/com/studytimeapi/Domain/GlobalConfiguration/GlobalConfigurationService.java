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
    public GlobalConfiguration updateGlobalConfiguration(DataUpdateGlobalConfiguration dataUpdateGlobalConfiguration) {
        GlobalConfiguration globalConfiguration = findGlobalConfigurationById(dataUpdateGlobalConfiguration.id());

        if (dataUpdateGlobalConfiguration.studentId() != null) {
            validationsIDsGlobalService.findStudentById(dataUpdateGlobalConfiguration.studentId());
        }

        globalConfiguration.update(dataUpdateGlobalConfiguration);
        return globalConfigurationRepository.save(globalConfiguration);
    }

    public DataListGlobalConfiguration configuration(Integer studentId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        return new DataListGlobalConfiguration(student.getGlobalConfiguration());
    }

    public GlobalConfiguration findGlobalConfigurationById(Integer configurationId) {
        return globalConfigurationRepository.findById(configurationId)
                .orElseThrow(() -> new RuntimeException("Configuración global no encontrada"));
    }
}
