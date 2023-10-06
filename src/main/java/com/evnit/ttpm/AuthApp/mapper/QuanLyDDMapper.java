package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryQuanLyDD;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;
import com.evnit.ttpm.AuthApp.entity.category.DMCXNK;

import com.evnit.ttpm.AuthApp.entity.common.SListGroupAll;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.QuanLyDDCreateDto;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.QuanLyDDListDto;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.TinhTPListDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienListDto;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglListDto;
import com.evnit.ttpm.AuthApp.service.common.SListAllService;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuanLyDDMapper {
    @Autowired
    SListAllService slstService;
	
    private final ModelMapper mapper;
    public QuanLyDDMapper(){
        this.mapper = new ModelMapper();
        configureMappings();
    }
    private void configureMappings(){
        mapper.createTypeMap(DMCXNK.class, QuanLyDDListDto.class)
      .addMappings(mapper ->{
     mapper.using(typeConvert).map(src -> src.getTYPE(), QuanLyDDListDto::setTYPENAME);
//       mapper.using(typeConvert).map(src -> src.getSCategoryTbaRgl().getTbaRglStatus(), QuanLyDDListDto::setTBASTATUS);
//    	 mapper.map(src -> src.getSCategoryTbaRgl().getTbaRglName(), QuanLyDDListDto::setTBANAME);
  	// mapper.map(src -> src.getSlit().getLISTDESC(), QuanLyDDListDto::setXNKNAME);
     //  mapper.using(typeConvert).map(src -> src.getSTATUS(), QuanLyDDListDto::setSTATUSNAME);
  // mapper.map(src -> getListDesc("TCDD", src.getPTGN1TC()), QuanLyDDListDto::setPTGN1TCNAME);
     	// mapper.map(src -> getListDesc("TCDD", src.getPTGN1TC()), QuanLyDDListDto::setPTGN1TCNAME);
   	 //mapper.map(src -> src.getSDeliveryUnit1().getName(), QuanLyDDListDto::setPTGN1DVGNNAME);
   	// mapper.map(src -> src.getSDeliveryUnit2().getName(), QuanLyDDListDto::setPTGN2DVGNNAME);


      });


        mapper.createTypeMap(QuanLyDDCreateDto.class, SCategoryQuanLyDD.class)
//                .addMappings(mapper -> mapper.map(src -> src.getCode(),SCategoryTbaRgl::setTbaRglCode))
//                .addMappings(mapper -> mapper.map(src -> src.getName(),SCategoryTbaRgl::setTbaRglName))
//                .addMappings(mapper -> mapper.map(src -> src.getCity(),SCategoryTbaRgl::setTbaRglCity))
//                .addMappings(mapper -> mapper.map(src -> src.getStatus(),SCategoryTbaRgl::setTbaRglStatus))
//                .addMappings(mapper -> mapper.map(src -> src.getXnk(),SCategoryTbaRgl::setTbaRglXnk))

        ;
    }
    
//    public List<QuanLyDDCreateDto> convertSCategoryQuanLyDDToQuanLyDDListDto(List<SCategoryQuanLyDD> listQuanLyDD, String groupName){
//        mapper.createTypeMap(SCategoryQuanLyDD.class, QuanLyDDListDto.class)
//      .addMappings(mapper ->{
//         mapper.using(typeConvert).map(src -> src.getTYPE(), QuanLyDDListDto::setTYPENAME);
//         mapper.using(typeConvert).map(src -> src.getSCategoryTbaRgl().getTbaRglStatus(), QuanLyDDListDto::setTBASTATUS);
//    	 mapper.map(src -> src.getSCategoryTbaRgl().getTbaRglName(), QuanLyDDListDto::setTBANAME);
//    	 mapper.map(src -> src.getSlit().getLISTDESC(), QuanLyDDListDto::setXNKNAME);
//         mapper.using(typeConvert).map(src -> src.getSTATUS(), QuanLyDDListDto::setSTATUSNAME);
//    	 mapper.map(src -> getListDesc("XNK", src.getPTGN1TC()), QuanLyDDListDto::setPTGN1TCNAME);
//      });
        
//        var result = listQuanLyDD.stream().map(QuanLyDD -> mapper.map(QuanLyDD, QuanLyDDListDto.class));       
//        return (List<QuanLyDDCreateDto>) result;
//    }
    
    private String getListDesc(String groupListId, int listOrd ) {
    	List<SListGroupAll> lsOrd = (List<SListGroupAll>) slstService.sListItemGetLst(groupListId).getData();
    	return lsOrd.stream().filter(x -> x.getGrouplistord() == listOrd).findFirst().get().getGrouplistdesc();
    }
    
    private Converter<Integer, String> typeConvert = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {
            Integer type = context.getSource();
            if (type != null) {
                switch (type) {
                    case 1:
                        return "NMD";
                    case 2:
                        return "TBA";
                    case 3:
                        return "RGL";
                }
            }
            return "";
        }
    };
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
