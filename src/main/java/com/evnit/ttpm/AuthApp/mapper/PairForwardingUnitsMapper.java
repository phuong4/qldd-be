package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.ViewSPairForwardingUnits;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

import com.evnit.ttpm.AuthApp.entity.category.SPairForwardingUnits;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsCreateDto;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsListDto;

@Configuration
public class PairForwardingUnitsMapper {
	private final ModelMapper mapper;
	
    public PairForwardingUnitsMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(SPairForwardingUnits.class, PairForwardingUnitsListDto.class)
        .addMappings(mapper -> {
            mapper.map(src -> src.getSDeliveryUnit().getName(),PairForwardingUnitsListDto::setUnit1Name);
            mapper.map(src -> src.getSDeliveryUnit1().getName(),PairForwardingUnitsListDto::setUnit2Name);
        })
        ;
        mapper.createTypeMap(PairForwardingUnitsCreateDto.class, SPairForwardingUnits.class)
                .addMappings(mapper -> mapper.map(PairForwardingUnitsCreateDto::getUnit1, SPairForwardingUnits::setUnit1))
                .addMappings(mapper -> mapper.map(PairForwardingUnitsCreateDto::getUnit2, SPairForwardingUnits::setUnit2))
                .addMappings(mapper -> mapper.map(PairForwardingUnitsCreateDto::getDescription, SPairForwardingUnits::setDescription))
        ;
        mapper.createTypeMap(ViewSPairForwardingUnits.class,PairForwardingUnitsListDto.class);
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
    public ModelMapper  getModelMapper() {
        return mapper;
    }
}
