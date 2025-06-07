package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.GlobalConfiguration.DataListGlobalConfiguration;
import jhomt.com.studytimeapi.Domain.GlobalConfiguration.DataUpdateGlobalConfiguration;
import jhomt.com.studytimeapi.Domain.GlobalConfiguration.GlobalConfigurationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/global-configurations")
public class GlobalConfigurationController {

    private final GlobalConfigurationService globalConfigurationService;

    public GlobalConfigurationController(GlobalConfigurationService globalConfigurationService) {
        this.globalConfigurationService = globalConfigurationService;
    }

    @PutMapping
    public ResponseEntity<?> updateGlobalConfiguration(@Valid @RequestBody DataUpdateGlobalConfiguration dataUpdateGlobalConfiguration) {
        try {
            DataListGlobalConfiguration updatedGlobalConfiguration = globalConfigurationService.updateGlobalConfiguration(dataUpdateGlobalConfiguration);
            return new ResponseEntity<>(updatedGlobalConfiguration, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating global configuration: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<?> findConfigurationByStudentId(@PathVariable Integer studentId) {
        try {
            DataListGlobalConfiguration globalConfiguration = globalConfigurationService.findConfigurationByStudentId(studentId);
            return new ResponseEntity<>(globalConfiguration, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving global configuration: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
