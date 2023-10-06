package com.evnit.ttpm.AuthApp.service.accreditation;

import com.evnit.ttpm.AuthApp.entity.accreditation.*;
import com.evnit.ttpm.AuthApp.repository.accreditation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AccreditationRepoService {

    @Autowired
    AccreditationDetailRepository accreditationDetailRepository;

    @Autowired
    AccreditationResultMeterRepository accreditationResultMeterRepository;

    @Autowired
    AccreditationResultTURepository accreditationResultTURepository;

    @Autowired
    AccreditationResultTIRepository accreditationResultTIRepository;

    @Autowired
    AccreditationRepository accreditationRepository;

    @Autowired
    ViewKiemDinhTableRepository viewKiemDinhTableRepository;

    public M_ACCREDITATION_DETAILS createAccreditationDetail(M_ACCREDITATION_DETAILS entity) {
        try {
            return accreditationDetailRepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save M_ACCREDITATION_DETAILS" + ignored.getMessage());
        }
    }

    public M_ACCREDITATION_RESULT_METER createAccreditationResultMeter(M_ACCREDITATION_RESULT_METER entity) {
        try {
            return accreditationResultMeterRepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save M_ACCREDITATION_RESULT_METER" + ignored.getMessage());
        }
    }

    public M_ACCREDITATION_RESULT_TU createAccreditationResultTU(M_ACCREDITATION_RESULT_TU entity) {
        try {
            return accreditationResultTURepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save M_ACCREDITATION_RESULT_TU" + ignored.getMessage());
        }
    }

    public M_ACCREDITATION_RESULT_TI createAccreditationResultTI(M_ACCREDITATION_RESULT_TI entity) {
        try {
            return accreditationResultTIRepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save M_ACCREDITATION_RESULT_TI" + ignored.getMessage());
        }
    }

    public M_ACCREDITATION createAccreditation(M_ACCREDITATION entity) {
        try {
            return accreditationRepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save M_ACCREDITATION" + ignored.getMessage());
        }
    }

    public Page<VIEW_KIEM_DINH_FINAL> findAllPaging(Specification<VIEW_KIEM_DINH_FINAL> spec, Pageable pageable) {
        try {
            var a = viewKiemDinhTableRepository.findAll(spec, pageable);
            return a;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
