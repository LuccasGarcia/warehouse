package com.ecommerce.warehouse.mapper;

import com.ecommerce.warehouse.controller.request.ProductSaveRequest;
import com.ecommerce.warehouse.controller.response.ProductDetailResponse;
import com.ecommerce.warehouse.controller.response.ProductSavedResponse;
import com.ecommerce.warehouse.dto.ProductStorefrontSaveDTO;
import com.ecommerce.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toSavedResponse(final ProductEntity entity);

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    ProductDetailResponse toDetailResponse(final ProductEntity entity);

}
