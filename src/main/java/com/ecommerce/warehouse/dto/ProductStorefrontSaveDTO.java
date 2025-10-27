package com.ecommerce.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ProductStorefrontSaveDTO(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name
) {
}
