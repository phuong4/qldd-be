package com.evnit.ttpm.AuthApp.mapper;
import com.evnit.ttpm.AuthApp.entity.category.View_Deal;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryThoaThuan;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanCreateDto;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanListDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienListDto;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThoaThuanMapper {
    private final ModelMapper mapper;
    public ThoaThuanMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(View_Deal.class, ThoaThuanListDto.class)
       // .addMappings(mapper -> mapper.using(domainConverter).map(src -> src.getDomain(), ThoaThuanListDto::setDomainStr))
//                .addMappings(mapper -> mapper.map(src -> src.getTbaRglCode(),TbaRglListDto::setCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;
        mapper.createTypeMap(ThoaThuanCreateDto.class, SCategoryThoaThuan.class)
//                .addMappings(mapper -> mapper.map(src -> src.getCode(),SCategoryTbaRgl::setTbaRglCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))

        ;
    }
    private Converter<Integer, String> domainConverter = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer domain = context.getSource();
            if (domain != null) {
                switch (domain) {
                    case 0:
                        return "Miền bắc";
                    case 1:
                        return "Miền trung";
                    case 2:
                        return "Miền nam";
                }
            }
            return "";
        }
    };
    public ModelMapper getModelMapper() {
        return mapper;
    }
}
