package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;
import com.evnit.ttpm.AuthApp.entity.category.v_DONVISH_PHANCAP;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHCreateDto;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHListDto;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglListDto;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DonViSHMapper {
    private final ModelMapper mapper;
    public DonViSHMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }

    	
        private void configureMappings(){
            mapper.createTypeMap(SCategoryDonViSH.class, DonViSHListDto.class)
                    .addMappings(mapper -> {
//                        mapper.map(src -> src.getSCategoryDonViSHByManage().getName(),DonViSHListDto::setParentStr);

                    })
            ;
            mapper.createTypeMap(v_DONVISH_PHANCAP.class, DonViSHListDto.class)
                    .addMappings(mapper -> {
                    })
            ;
        }


    private Converter<Integer, String> statusConverter = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer status = context.getSource();
            if (status != null) {
                switch (status) {
                    case 1:
                        return "Đang hoạt động";
                    case 0:
                        return "Ngừng hoạt động";
                }
            }
            return "";
        }
    };
    public ModelMapper getModelMapper() {
        return mapper;
    }
}
