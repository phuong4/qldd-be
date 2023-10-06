package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;
import com.evnit.ttpm.AuthApp.entity.category.ViewTbaRgl;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglListDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TbaRglMapper {
    private final ModelMapper mapper;

    public TbaRglMapper() {
        this.mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        configureMappings();
    }

    private void configureMappings() {
        mapper.createTypeMap(SCategoryTbaRgl.class, TbaRglListDto.class)
                .addMappings(mapper -> {
                    mapper.using(statusConverter)
                            .map(SCategoryTbaRgl::getTbaRglStatus, TbaRglListDto::setStatusStr);
//                    mapper.using(ConvertData)
//                            .map(src -> src, TbaRglListDto::setOwnershipUnitStr);
                    mapper.map(src -> src.getSCategoryTinhTP().getName(), TbaRglListDto::setCityName);

                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getName(), TbaRglListDto::setOwnershipUnitStr);
                    mapper.map(src -> src.getSCategoryDonViSHByManage().getName(), TbaRglListDto::setManageUnitStr);
                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getType(), TbaRglListDto::setOwnerType);
                    mapper.map(src -> src.getSCategoryDonViSHByManage().getType(), TbaRglListDto::setManageType);
                    mapper.using(typeConvert)
                            .map(SCategoryTbaRgl::getType, TbaRglListDto::setTypeStr);
                })
        ;
        mapper.createTypeMap(ViewTbaRgl.class, TbaRglListDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getType(), TbaRglListDto::setOwnerType);
                    mapper.map(src -> src.getSCategoryDonViSHByManage().getType(), TbaRglListDto::setManageType);
                    mapper.map(ViewTbaRgl::getTbaRglStatus, TbaRglListDto::setStatus);
                    mapper.map(ViewTbaRgl::getType, TbaRglListDto::setType);
                    mapper.map(ViewTbaRgl::getTbaRglCity, TbaRglListDto::setCity);
                    mapper.map(ViewTbaRgl::getCityName, TbaRglListDto::setCityName);
                    mapper.map(ViewTbaRgl::getTbaRglName, TbaRglListDto::setName);
                    mapper.map(ViewTbaRgl::getTbaRglCode, TbaRglListDto::setCode);
                    mapper.map(ViewTbaRgl::getTbaRglXnk, TbaRglListDto::setXnk);
                })
        ;
        mapper.createTypeMap(TbaRglCrudDto.class, SCategoryTbaRgl.class)
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getCode, SCategoryTbaRgl::setTbaRglCode))
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getName, SCategoryTbaRgl::setTbaRglName))
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getCity, SCategoryTbaRgl::setTbaRglCity))
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getStatus, SCategoryTbaRgl::setTbaRglStatus))
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getXnk, SCategoryTbaRgl::setTbaRglXnk))
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getOwnershipUnit, SCategoryTbaRgl::setOwnerShipUnit))
                .addMappings(mapper -> mapper.map(TbaRglCrudDto::getManageUnit, SCategoryTbaRgl::setManageUnit))

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
                    case 2:
                        return "Chưa vận hành";
                    case 0:
                        return "Huỷ";
                }
            }
            return "";
        }
    };
    //    private Converter<SCategoryTbaRgl, String> ConvertData = new Converter<SCategoryTbaRgl, String>() {
//        @Override
//        public String convert(MappingContext<SCategoryTbaRgl, String> context) {
//            SCategoryTbaRgl entity = context.getSource();
//            Integer status = entity.getType();
//            String name = entity.getTbaRglName();
//            if (status != null) {
//                switch (status) {
//                    case 1:
//                        return name;
//                    case 0:
//                        return "Ngừng hoạt động";
//                }
//            }
//            return "";
//        }
//    };
    private Converter<Integer, String> typeConvert = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer type = context.getSource();
            if (type != null) {
                switch (type) {
                    case 0:
                        return "TBA";
                    case 1:
                        return "RGL";
                }
            }
            return "";
        }
    };

    public ModelMapper getModelMapper() {
        return mapper;
    }
}
