package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglListDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

import com.evnit.ttpm.AuthApp.entity.category.SElectricFactory;
import com.evnit.ttpm.AuthApp.entity.category.ViewElectricFactory;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienCrudDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienListDto;

@Configuration
public class NhaMayDienMapper {
    private final ModelMapper mapper;

    public NhaMayDienMapper() {
        this.mapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        mapper.createTypeMap(SElectricFactory.class, NhaMayDienListDto.class)
                .addMappings(mapper -> {
                    mapper.using(statusConverter).map(src -> src.getStatus(), NhaMayDienListDto::setStatusStr);
                    mapper.using(regionConverter).map(src -> src.getRegions(), NhaMayDienListDto::setRegionsName);
                    mapper.map(src -> src.getSCategoryTinhTP().getName(), NhaMayDienListDto::setCityName);
                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getName(),NhaMayDienListDto::setOwnershipUnitStr);
                    mapper.map(src -> src.getSCategoryDonViSHByManage().getName(),NhaMayDienListDto::setManageUnitStr);
                })

//                .addMappings(mapper -> mapper.map(src -> src.getTbaRglCode(),TbaRglListDto::setCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))
        ;
        mapper.createTypeMap(NhaMayDienCrudDto.class, SElectricFactory.class)
//                .addMappings(mapper -> mapper.map(src -> src.getCode(),SElectricFactory::set))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SElectricFactory::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SElectricFactory::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SElectricFactory::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SElectricFactory::setTbaRglXnk))

        ;
        mapper.createTypeMap(ViewElectricFactory.class, NhaMayDienListDto.class)
                .addMappings(mapper -> {
                    mapper.using(statusConverter).map(src -> src.getStatus(), NhaMayDienListDto::setStatusStr);
                    mapper.using(regionConverter).map(src -> src.getRegions(), NhaMayDienListDto::setRegionsName);
                    mapper.map(src -> src.getSCategoryTinhTP().getName(), NhaMayDienListDto::setCityName);
                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getName(),NhaMayDienListDto::setOwnershipUnitStr);
                    mapper.map(src -> src.getSCategoryDonViSHByManage().getName(),NhaMayDienListDto::setManageUnitStr);
                });
    }

    private Converter<Integer, String> statusConverter = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer status = context.getSource();
            if (status != null) {
                switch (status) {
                    case 1:
                        return "Đang vận hành";
                    case 2:
                        return "Ngừng vận hành";
                    case 0:
                        return "Huỷ";
                }
            }
            return "";
        }
    };
    private Converter<Integer, String> regionConverter = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer regions = context.getSource();
            if (regions != null) {
                switch (regions) {
                    case 1:
                        return "Miền Trung";
                    case 2:
                        return "Miền Nam";
                    case 0:
                        return "Miền Bắc";
                }
            }
            return "";
        }
    };

    public ModelMapper getModelMapper() {
        return mapper;
    }

}
