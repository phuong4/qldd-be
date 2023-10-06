package com.evnit.ttpm.AuthApp.service.category;
import com.evnit.ttpm.AuthApp.entity.category.SPairForwardingUnits;
import com.evnit.ttpm.AuthApp.entity.category.ViewSPairForwardingUnits;
import com.evnit.ttpm.AuthApp.repository.category.PairForwardingUnitsRepository;
import com.evnit.ttpm.AuthApp.repository.category.View_PairForwardingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
@Service
public class SPairForwardingUnitsRepoService {
    @Autowired
    PairForwardingUnitsRepository pairForwardingUnitsRepository;
    @Autowired
    View_PairForwardingUnit view_pairForwardingUnit;


    public Page<ViewSPairForwardingUnits> findAll(Specification<ViewSPairForwardingUnits> spec, Pageable pageable) {
        return view_pairForwardingUnit.findAll(spec,pageable);
    }
    public Page<SPairForwardingUnits> findAllPaging(Specification<SPairForwardingUnits> spec, Pageable pageable) {
        return pairForwardingUnitsRepository.findAll(spec,pageable);
    }
}
