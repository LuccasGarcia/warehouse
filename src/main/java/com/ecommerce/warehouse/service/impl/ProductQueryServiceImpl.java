package com.ecommerce.warehouse.service.impl;

import com.ecommerce.warehouse.entity.ProductEntity;
import com.ecommerce.warehouse.repository.ProductRepository;
import com.ecommerce.warehouse.service.IProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements IProductQueryService {

    private final ProductRepository repository;

    @Override
    public ProductEntity findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
