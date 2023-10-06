package com.evnit.ttpm.AuthApp.service.category;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;
import com.evnit.ttpm.AuthApp.repository.category.TbaRglRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SCategoryTbaRglRepoService {
    @Autowired
    TbaRglRepository tbaRglRepository;

    public Page<SCategoryTbaRgl> findAllPaging(Specification<SCategoryTbaRgl> spec, Pageable pageable){
        return tbaRglRepository.findAll(spec,pageable);
    }
}
