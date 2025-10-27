package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductQueryService {

    ProductEntity findById(final UUID id);
}
