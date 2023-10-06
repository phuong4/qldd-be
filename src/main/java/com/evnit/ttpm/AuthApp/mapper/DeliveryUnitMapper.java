package com.evnit.ttpm.AuthApp.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitCreateDto;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitListDto;

@Configuration
public class DeliveryUnitMapper {
	
	private final ModelMapper mapper;
    public DeliveryUnitMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(SDeliveryUnit.class, DeliveryUnitListDto.class)
        
//                .addMappings(mapper -> mapper.using(statusConverter).map(src -> src.getStatus(), LicenseOperateListDto::setStatusStr))
//                .addMappings(mapper -> mapper.map(src -> src.getStartDate() ,DeliveryUnitListDto::setStartDateStr))
//                .addMappings(mapper -> mapper.map(src -> src.getEndDate() ,DeliveryUnitListDto::setEndDateStr))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;
        mapper.createTypeMap(DeliveryUnitCreateDto.class, SDeliveryUnit.class)
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
