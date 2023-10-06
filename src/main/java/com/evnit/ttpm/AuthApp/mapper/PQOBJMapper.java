package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.PARAMATER;
import com.evnit.ttpm.AuthApp.entity.category.Q_PQOBJ_USER;
import com.evnit.ttpm.AuthApp.model.PQOBJ.PQOBJCreateDto;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PQOBJMapper {
    private final ModelMapper mapper;
    public PQOBJMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){

        mapper.createTypeMap(Q_PQOBJ_USER.class, PQOBJCreateDto.class )
                //.addMappings(mapper -> mapper.map(src -> src.getDateEvent() , ParamaterCreateDto::setDateEventStr))
//                .addMappings(mapper -> mapper.map(src -> src.getCode(),SCategoryTbaRgl::setTbaRglCode))
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
