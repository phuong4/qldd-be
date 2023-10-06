package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.SCategorySuCoBatThuong;
import com.evnit.ttpm.AuthApp.entity.category.View_Problem;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoListDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SuCoMapper {
    private final ModelMapper mapper;
    public SuCoMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(View_Problem.class, SuCoListDto.class)
       // .addMappings(mapper -> mapper.using(domainConverter).map(src -> src.getDomain(), SuCoListDto::setDomainStr))
//                .addMappings(mapper -> mapper.map(src -> src.getTbaRglCode(),TbaRglListDto::setCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;
        mapper.createTypeMap(SuCoCreateDto.class, SCategorySuCoBatThuong.class)
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
