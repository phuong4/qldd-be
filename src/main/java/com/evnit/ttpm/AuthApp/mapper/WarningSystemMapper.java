package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.PlanAccreditation;
import com.evnit.ttpm.AuthApp.entity.category.WarningSystem;
import com.evnit.ttpm.AuthApp.model.category.Plan.PlanListDto;
import com.evnit.ttpm.AuthApp.model.category.WarningSystem.WarningSystemListDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarningSystemMapper {
    private final ModelMapper mapper;
    public WarningSystemMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(WarningSystem.class, WarningSystemListDto.class)
       // .addMappings(mapper -> mapper.using(domainConverter).map(src -> src.getDomain(), PlanListDto::setDomainStr))
//                .addMappings(mapper -> mapper.map(src -> src.getTbaRglCode(),TbaRglListDto::setCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;

    }
    public ModelMapper getModelMapper() {
        return mapper;
    }
}
