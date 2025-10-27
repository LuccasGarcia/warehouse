package com.ecommerce.warehouse.controller;

import com.ecommerce.warehouse.controller.request.ProductSaveRequest;
import com.ecommerce.warehouse.controller.response.ProductDetailResponse;
import com.ecommerce.warehouse.controller.response.ProductSavedResponse;
import com.ecommerce.warehouse.mapper.IProductMapper;
import com.ecommerce.warehouse.service.IProductQueryService;
import com.ecommerce.warehouse.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final IProductService service;
    private final IProductQueryService queryService;
    private final IProductMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    ProductSavedResponse create(@RequestBody final ProductSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toSavedResponse(entity);
    }

    @PostMapping("{id}/purchase")
    @ResponseStatus(NO_CONTENT)
    void purchase(@PathVariable final UUID id){
        service.purchase(id);
    }

    @GetMapping("{id}")
    ProductDetailResponse findById(@PathVariable final UUID id){
        var dto = queryService.findById(id);
        return mapper.toDetailResponse(dto);
    }
}
