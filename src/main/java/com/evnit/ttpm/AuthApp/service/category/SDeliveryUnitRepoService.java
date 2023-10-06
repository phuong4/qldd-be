package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import com.evnit.ttpm.AuthApp.entity.category.SElectricFactory;
import com.evnit.ttpm.AuthApp.repository.category.DeliveryUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SDeliveryUnitRepoService {
    @Autowired
    DeliveryUnitRepository deliveryUnitRepository;

    public Page<SDeliveryUnit> findAll(Specification<SDeliveryUnit> spec, Pageable pageable) {
        return deliveryUnitRepository.findAll(spec,pageable);
    }
}
