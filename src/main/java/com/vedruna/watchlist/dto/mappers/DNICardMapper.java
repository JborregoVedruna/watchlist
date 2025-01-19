package com.vedruna.watchlist.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vedruna.watchlist.dto.entitiesdtos.DNICardDTO;
import com.vedruna.watchlist.persistance.model.DNICard;

@Mapper(componentModel = "spring")
public interface DNICardMapper extends BaseMapper<DNICard, DNICardDTO> {

    @Mapping(source = "dniOwner.userId", target = "dniOwnerId")
    DNICardDTO entityToDTO(DNICard entity);
}