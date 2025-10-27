package com.ecommerce.warehouse.controller;

import com.ecommerce.warehouse.controller.request.StockSaveRequest;
import com.ecommerce.warehouse.controller.response.StockSavedResponse;
import com.ecommerce.warehouse.mapper.IStockMapper;
import com.ecommerce.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("stocks")
@AllArgsConstructor
public class StockController {

    private final IStockService service;
    private final IStockMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    StockSavedResponse save(@RequestBody final StockSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toResponse(entity);
    }

    @PutMapping("{id}/release")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void release(@PathVariable final UUID id) {
        service.release(id);
    }

    @DeleteMapping("{id}/release")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inactive(@PathVariable final UUID id) {
        service.inactive(id);
    }
}
