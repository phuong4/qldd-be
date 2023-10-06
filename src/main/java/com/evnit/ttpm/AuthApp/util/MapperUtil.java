package com.evnit.ttpm.AuthApp.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {
    private final ModelMapper modelMapper;
    public MapperUtil(){
        this.modelMapper = new ModelMapper();
    }
    public <D,E> D convertToDto(E entity, Class<D> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }
    public <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
    public <D, E> List<D> convertToDtoList(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }
}
