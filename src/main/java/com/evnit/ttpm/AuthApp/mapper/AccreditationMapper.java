package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.accreditation.VIEW_KIEM_DINH_FINAL;
import com.evnit.ttpm.AuthApp.model.accreditation.KiemDinhListDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccreditationMapper {
    private final ModelMapper mapper;
    public AccreditationMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings(){
        mapper.createTypeMap(VIEW_KIEM_DINH_FINAL.class, KiemDinhListDto.class)
                .addMappings(mapper -> {
                })
        ;
    }

    public ModelMapper getModelMapper() {
        return mapper;
    }
}
