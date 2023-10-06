package com.evnit.ttpm.AuthApp.service.device;

import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuonTI;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ViewCuonRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ViewCuonTIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuonService {
    private final ViewCuonRepository viewCuonRepository;
    private final ViewCuonTIRepository viewCuonTIRepository;
    @Autowired
    public CuonService(ViewCuonRepository viewCuonRepository, ViewCuonTIRepository viewCuonTIRepository) {
        this.viewCuonRepository = viewCuonRepository;
        this.viewCuonTIRepository = viewCuonTIRepository;
    }
    public Optional<ViewCuon> findCuonById(String id){
        return viewCuonRepository.findById(id);
    }public Optional<ViewCuonTI> findCuonTIById(String id){
        return viewCuonTIRepository.findById(id);
    }
}
