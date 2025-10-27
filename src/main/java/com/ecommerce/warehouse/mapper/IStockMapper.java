package com.ecommerce.warehouse.mapper;

import com.ecommerce.warehouse.controller.request.StockSaveRequest;
import com.ecommerce.warehouse.controller.response.StockSavedResponse;
import com.ecommerce.warehouse.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IStockMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "status", expression = "java(com.ecommerce.warehouse.entity.StockStatus.IN_CONFERENCE)")
    StockEntity toEntity(final StockSaveRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    StockSavedResponse toResponse(final StockEntity entity);
}
