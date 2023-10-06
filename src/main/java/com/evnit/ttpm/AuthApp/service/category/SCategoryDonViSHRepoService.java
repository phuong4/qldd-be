package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import com.evnit.ttpm.AuthApp.repository.category.DonViSHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SCategoryDonViSHRepoService {
    @Autowired
    DonViSHRepository donViSHRepository;

    public Page<SCategoryDonViSH> findAllPaging(Specification<SCategoryDonViSH> spec, Pageable pageable){
        return donViSHRepository.findAll(spec,pageable);
    }
}
