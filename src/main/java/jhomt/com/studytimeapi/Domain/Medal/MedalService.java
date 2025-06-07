package jhomt.com.studytimeapi.Domain.Medal;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedalService {

    private final MedalRepository medalRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public MedalService(MedalRepository medalRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.medalRepository = medalRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public Medal registerMedal(DataRegisterMedal dataRegisterMedal) {
        Medal medal = new Medal(dataRegisterMedal);
        return medalRepository.save(medal);
    }

    @Transactional
    public Medal updateMedal(DataUpdateMedal dataUpdateMedal) {
        Medal medal = validationsIDsGlobalService.findMedalById(dataUpdateMedal.id());
        medal.update(dataUpdateMedal);
        return medalRepository.save(medal);
    }

    public List<DataListMedal> listMedals() {
        List<Medal> medals = medalRepository.findAll();
        return medals.stream()
                .map(DataListMedal::new)
                .toList();
    }
}
