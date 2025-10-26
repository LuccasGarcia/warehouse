package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    ProductEntity findById(final UUID id);

    void purchase(final UUID id);
}
