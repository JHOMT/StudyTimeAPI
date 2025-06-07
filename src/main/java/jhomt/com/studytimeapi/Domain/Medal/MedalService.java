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
    public DataListMedal registerMedal(DataRegisterMedal dataRegisterMedal) {
        Medal medal = new Medal(dataRegisterMedal);
        medal = medalRepository.save(medal);
        return new DataListMedal(medal);
    }

    @Transactional
    public DataListMedal updateMedal(DataUpdateMedal dataUpdateMedal) {
        Medal medal = validationsIDsGlobalService.findMedalById(dataUpdateMedal.id());
        medal.update(dataUpdateMedal);
        medal = medalRepository.save(medal);
        return new DataListMedal(medal);
    }

    public List<DataListMedal> listMedals() {
        List<Medal> medals = medalRepository.findAll();
        return medals.stream()
                .map(DataListMedal::new)
                .toList();
    }
}
