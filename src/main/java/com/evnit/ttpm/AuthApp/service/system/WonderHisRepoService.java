package com.evnit.ttpm.AuthApp.service.system;

import com.evnit.ttpm.AuthApp.entity.accreditation.M_ACCREDITATION_DETAILS;
import com.evnit.ttpm.AuthApp.entity.system.M_WORDER_HIS;
import com.evnit.ttpm.AuthApp.repository.accreditation.AccreditationDetailRepository;
import com.evnit.ttpm.AuthApp.repository.system.WorderHisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WonderHisRepoService {
    @Autowired
    WorderHisRepository worderHisRepository;

    public M_WORDER_HIS createMWorderHis(M_WORDER_HIS entity) {
        try {
            return worderHisRepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save M_WORDER_HIS" + ignored.getMessage());
        }
    }
}
