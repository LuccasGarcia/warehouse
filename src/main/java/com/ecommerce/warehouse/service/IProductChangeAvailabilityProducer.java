package com.ecommerce.warehouse.service;

import com.ecommerce.warehouse.dto.StockStatusMessage;

public interface IProductChangeAvailabilityProducer {

    void notifyStatusChange(final StockStatusMessage message);
}
