package dev.gustavoteixeira.easygest.adapter.primary.http.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UpdateProductRequest {

    String description;

    BigDecimal price;

}
