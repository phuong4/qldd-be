package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryTinhTP;
import com.evnit.ttpm.AuthApp.entity.category.ViewTinhTP;
import com.evnit.ttpm.AuthApp.repository.category.TinhTPRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewTinhTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SCategoryTinhTPRepoService {
    @Autowired
    ViewTinhTPRepository tinhTPRepository;

    public Page<ViewTinhTP> findAll(Specification<ViewTinhTP> spec, Pageable pageable) {
        return tinhTPRepository.findAll(spec,pageable);
    }
}

