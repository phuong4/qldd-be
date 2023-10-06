package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.acceptance.Acceptance;
import com.evnit.ttpm.AuthApp.entity.acceptance.ViewAcceptanceFinal;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceCrudDto;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceListDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcceptanceMapper {
    private final ModelMapper mapper;

    public AcceptanceMapper() {
        this.mapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        mapper.createTypeMap(Acceptance.class, AcceptanceCrudDto.class);
        mapper.createTypeMap(ViewAcceptanceFinal.class, AcceptanceListDto.class)
                .addMappings(mapper -> {
                    mapper.using(statusConverter).map(ViewAcceptanceFinal::getPowerSystemStatus, AcceptanceListDto::setPowerSystemStatusStr);
                    mapper.using(typeAcceptConverter).map(ViewAcceptanceFinal::getTypeAccept, AcceptanceListDto::setTypeAcceptStr);
                });
    }

    public ModelMapper getModelMapper() {
        return this.mapper;
    }

    private final Converter<Integer, String> statusConverter = new Converter<Integer, String>() {
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
                    case -1:
                    case 3:
                        return "Kiểm tra lắp đặt";
                }
            }
            return "";
        }
    };
    private final Converter<String, String> typeAcceptConverter = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> context) {
            String status = context.getSource();
            if (status != null) {
                switch (status) {
                    case "KTLD":
                        return "Kiểm tra lắp đặt";
                    case "NTTLD":
                        return "NT tĩnh lần đầu";
                    case "NTMTLD":
                        return "NT mang tải lần đầu";
                    case "TTTBDD":
                        return "NT thay thế thiết bị đo đếm";
                    case "NTK":
                        return "NT khác";
                    case "HDD":
                        return "Huỷ điểm đo";
                }
            }
            return "";
        }
    };
}
