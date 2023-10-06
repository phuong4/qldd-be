package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import com.evnit.ttpm.AuthApp.entity.category.v_DONVISH_PHANCAP;
import com.evnit.ttpm.AuthApp.repository.category.DonViSHPhanCapRepository;
import com.evnit.ttpm.AuthApp.repository.category.DonViSHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;



@Service
public class ViewDonViSHPhanCapRepoService {
    @Autowired
    DonViSHPhanCapRepository donViSHPhanCapRepository;

    public Page<v_DONVISH_PHANCAP> findAllPaging(Specification<v_DONVISH_PHANCAP> spec, Pageable pageable){
        try {
            var a =donViSHPhanCapRepository.findAll(spec,pageable);
            return  a;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}