package com.evnit.ttpm.AuthApp.service.category;
import com.evnit.ttpm.AuthApp.entity.category.SElectricFactory;
import com.evnit.ttpm.AuthApp.entity.category.ViewElectricFactory;
import com.evnit.ttpm.AuthApp.repository.category.NhaMayDienRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewNhaMayDienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SNhaMayDienRepoService {
    @Autowired
    NhaMayDienRepository nhaMayDienRepository;
    @Autowired
    ViewNhaMayDienRepository viewNhaMayDienRepository;

    public Page<SElectricFactory> findAllPaging(Specification<SElectricFactory> spec, Pageable pageable) {
        return nhaMayDienRepository.findAll(spec,pageable);
    }
    public Page<ViewElectricFactory> findAll(Specification<ViewElectricFactory> spec, Pageable pageable) {
        return viewNhaMayDienRepository.findAll(spec,pageable);
    }
}
