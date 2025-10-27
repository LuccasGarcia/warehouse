package com.ecommerce.warehouse.service.impl;

import com.ecommerce.warehouse.dto.ProductStorefrontSaveDTO;
import com.ecommerce.warehouse.entity.ProductEntity;
import com.ecommerce.warehouse.mapper.IProductMapper;
import com.ecommerce.warehouse.repository.ProductRepository;
import com.ecommerce.warehouse.service.IProductQueryService;
import com.ecommerce.warehouse.service.IProductService;
import com.ecommerce.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final IProductQueryService queryService;
    private final IStockService stockService;
    private final RestClient storefrontClient;
    private final IProductMapper mapper;

    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStorefront(dto);
        return entity;
    }

    @Override
    public void purchase(UUID id) {
        var entity = queryService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);
        if (stock.isUnavailable()){
            stockService.changeStatus(stock.getId(), stock.getStatus());
        }
    }

    private void saveStorefront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSaveDTO.class);
    }
}
