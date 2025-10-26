package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.entity.StockEntity;
import com.ecommerce.warehouse.entity.StockStatus;

import java.util.UUID;

public interface IStockService {

    StockEntity save(final StockEntity entity);

    void release(final UUID id);

    void inactive(final UUID id);

    void changeStatus(final UUID id, final StockStatus status);
}
