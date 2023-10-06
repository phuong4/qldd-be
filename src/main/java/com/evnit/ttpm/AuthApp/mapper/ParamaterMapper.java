package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.PARAMATER;
import com.evnit.ttpm.AuthApp.entity.category.SCategorySuCoBatThuong;
import com.evnit.ttpm.AuthApp.entity.category.View_Problem;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateListDto;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoListDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParamaterMapper {
    private final ModelMapper mapper;
    public ParamaterMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){

        mapper.createTypeMap(PARAMATER.class,ParamaterCreateDto.class )
                .addMappings(mapper -> mapper.map(src -> src.getDateEvent() , ParamaterCreateDto::setDateEventStr))
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
