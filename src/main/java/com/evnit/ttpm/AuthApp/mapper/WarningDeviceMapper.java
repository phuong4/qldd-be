package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.WarningDevice;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.WarningDeviceListDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarningDeviceMapper {
    private final ModelMapper mapper;
    public WarningDeviceMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(WarningDevice.class, WarningDeviceListDto.class)
                .addMappings(mapper-> mapper.using(typeConvert).map(src-> src.getType(), WarningDeviceListDto::setTypeStr))
                .addMappings(mapper-> mapper.map(src->src.getErrorOriginTUTI(),WarningDeviceListDto::setErrorOriginTUTI1))
                .addMappings(mapper-> mapper.map(src->src.getErrorOriginTUTIYes(),WarningDeviceListDto::setErrorOriginTUTIYes1))
                .addMappings(mapper-> mapper.map(src->src.getErrorScoreTUTI(),WarningDeviceListDto::setErrorScoreTUTI1))
                .addMappings(mapper-> mapper.map(src->src.getErrorScoreTUTIYes(),WarningDeviceListDto::setErrorScoreTUTIYes1));
       // .addMappings(mapper -> mapper.using(domainConverter).map(src -> src.getDomain(), PlanListDto::setDomainStr))
//                .addMappings(mapper -> mapper.map(src -> src.getTbaRglCode(),TbaRglListDto::setCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
       /* mapper.createTypeMap(WarningDeviceListDto.class,WarningDevice.class )
                .addMappings(mapper-> mapper.map(src->src.getErrorOriginTUTI1(),WarningDevice::setErrorOriginTUTI))
                .addMappings(mapper-> mapper.map(src->src.getErrorOriginTUTIYes1(),WarningDevice::setErrorOriginTUTIYes))
                .addMappings(mapper-> mapper.map(src->src.getErrorScoreTUTI1(),WarningDevice::setErrorScoreTUTI))
                .addMappings(mapper-> mapper.map(src->src.getErrorScoreTUTIYes1(),WarningDevice::setErrorScoreTUTIYes));*/

    }
    public ModelMapper getModelMapper() {
        return mapper;
    }
    private Converter<Integer, String> typeConvert = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer type = context.getSource();
            if (type != null) {
                switch (type) {
                    case 1:
                        return "Công tơ";
                    case 2:
                        return "TU";
                    case 3:
                        return "TI";
                }
            }
            return "";
        }
    };
}
