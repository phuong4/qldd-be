package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.accreditation.ViewAccreditationDevice;
import com.evnit.ttpm.AuthApp.entity.category.*;
import com.evnit.ttpm.AuthApp.entity.common.ALstType;
import com.evnit.ttpm.AuthApp.entity.common.OptionSelect;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglListDto;
import com.evnit.ttpm.AuthApp.model.request.common.SelectItem;
import com.evnit.ttpm.AuthApp.model.request.common.TreeSelect;
import com.evnit.ttpm.AuthApp.repository.accreditation.ViewAccreditationDeviceRepo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class LookupMapper {
    private final ModelMapper modelMapper;

    public LookupMapper() {
        this.modelMapper = new ModelMapper();
        this.configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(ViewAccreditationDevice.class, SelectItem.class)
                .addMappings(mapper -> {
                    mapper.using(textConvert).map(src -> src, SelectItem::setText);
                    mapper.map(ViewAccreditationDevice::getAccredId, SelectItem::setValue);
                    mapper.map(ViewAccreditationDevice::getAccredId, SelectItem::setId);
                });
        modelMapper.createTypeMap(SCategoryDonViSH.class, TreeSelect.class)
                .addMappings(mapper -> {
                    mapper.map(SCategoryDonViSH::getId, TreeSelect::setValue);
                });
        modelMapper.createTypeMap(SCategoryTinhTP.class, SelectItem.class)
                .addMappings(mapper -> {
                    mapper.map(SCategoryTinhTP::getId, SelectItem::setValue);
                    mapper.map(SCategoryTinhTP::getName, SelectItem::setText);
                });
        modelMapper.createTypeMap(SCategoryDonViSH.class, SelectItem.class)
                .addMappings(mapper -> {
                    mapper.map(SCategoryDonViSH::getId, SelectItem::setValue);
                    mapper.map(SCategoryDonViSH::getName, SelectItem::setText);
                });

        modelMapper.createTypeMap(SDeliveryUnit.class, SelectItem.class)
                .addMappings(mapper -> {

                    mapper.map(src -> src.getId(), SelectItem::setValue);
                    mapper.map(src -> src.getName(), SelectItem::setText);
                });
        modelMapper.createTypeMap(SCategoryTbaRgl.class, SelectItem.class)
                .addMappings(mapper -> {
                    mapper.map(SCategoryTbaRgl::getId, SelectItem::setValue);
                    mapper.map(SCategoryTbaRgl::getType, SelectItem::setDeviceType);
                    mapper.map(SCategoryTbaRgl::getTbaRglStatus, SelectItem::setStatus);

                    mapper.map(SCategoryTbaRgl::getTbaRglName, SelectItem::setText);
                });
        modelMapper.createTypeMap(ALstType.class, SelectItem.class)
                .addMappings(mapper -> {
                    mapper.map(ALstType::getTypeId, SelectItem::setValue);
                    mapper.map(ALstType::getTypeDesc, SelectItem::setText);
                });
        modelMapper.createTypeMap(SElectricFactory.class, SelectItem.class)
                .addMappings(mapper -> {
                    mapper.map(SElectricFactory::getId, SelectItem::setValue);
                    mapper.map(SElectricFactory::getTenNM, SelectItem::setText);
                    mapper.map(SElectricFactory::getStatus, SelectItem::setStatus);

                });
    }

    private Converter<ViewAccreditationDevice, String> textConvert = new Converter<ViewAccreditationDevice, String>() {
        @Override
        public String convert(MappingContext<ViewAccreditationDevice, String> context) {
            var viewAccred = context.getSource();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (viewAccred != null) {
                var startDate = df.format(viewAccred.getAccreditationStartDate());
                var endDate = df.format(viewAccred.getAccreditationEndDate());
                var deviceName = viewAccred.getDeviceName();
                var pha = viewAccred.getPha();
                String suffix = "";
                if ((viewAccred.getDeviceType().contains("TU") || viewAccred.getDeviceType().contains("TI")) && !pha.isEmpty()) {
                    suffix = "pha " + pha;
                }
                return startDate + ":" + endDate + "; " + deviceName + " " + suffix;
            }
            return "";
        }
    };

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
