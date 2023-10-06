package com.evnit.ttpm.AuthApp.mapper;

import java.util.Date;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

import com.evnit.ttpm.AuthApp.entity.category.SLicenseOperate;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateCreateDto;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateListDto;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

@Configuration
public class LicenseOperateMapper {
	
	private final ModelMapper mapper;
    public LicenseOperateMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(SLicenseOperate.class, LicenseOperateListDto.class)
//                .addMappings(mapper -> mapper.using(statusConverter).map(src -> src.getStatus(), LicenseOperateListDto::setStatusStr))
                .addMappings(mapper -> mapper.map(src -> src.getStartDate() ,LicenseOperateListDto::setStartDateStr))
                .addMappings(mapper -> mapper.map(src -> src.getEndDate() ,LicenseOperateListDto::setEndDateStr))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;
        mapper.createTypeMap(LicenseOperateCreateDto.class, SLicenseOperate.class)
//                .addMappings(mapper -> mapper.map(src -> src.getCode(),SElectricFactory::set))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SElectricFactory::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SElectricFactory::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SElectricFactory::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SElectricFactory::setTbaRglXnk))

        ;
    }
    private Converter<Integer, String> statusConverter = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer status = context.getSource();
            if (status != null) {
                switch (status) {
                    case 1:
                        return "Đang vận hành";
                    case 0:
                        return "Ngừng vận hành";
                }
            }
            return "";
        }
    };
    public ModelMapper getModelMapper() {
        return mapper;
    }
}
