package com.evnit.ttpm.AuthApp.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

import com.evnit.ttpm.AuthApp.entity.category.SEngineUnit;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitCreateDto;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitListDto;

@Configuration
public class EngineUnitMapper {
	private final ModelMapper mapper;
    public EngineUnitMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(SEngineUnit.class, EngineUnitListDto.class)
                .addMappings(mapper -> mapper.using(activeConverter).map(src -> src.getActive(), EngineUnitListDto::setActiveStr))
                .addMappings(mapper -> mapper.map(src -> src.getActive(),EngineUnitListDto::setCong_suat_max))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;
        mapper.createTypeMap(EngineUnitCreateDto.class, SEngineUnit.class)
//                .addMappings(mapper -> mapper.map(src -> src.getCode(),SElectricFactory::set))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SElectricFactory::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SElectricFactory::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SElectricFactory::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SElectricFactory::setTbaRglXnk))

        ;
    }
    private Converter<Boolean, String> activeConverter = new Converter<Boolean, String>() {
        @Override
        public String convert(MappingContext<Boolean, String> context) {
        	Boolean active = context.getSource();
        	if (active != null) {
                if (active) {
                    return "Có";
                } else {
                    return "Không";
                }
            }
            return "";
        }
    };
    public ModelMapper getModelMapper() {
        return mapper;
    }
}
