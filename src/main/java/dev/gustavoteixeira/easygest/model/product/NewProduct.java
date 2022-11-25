package dev.gustavoteixeira.easygest.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class NewProduct {

    String description;

    BigDecimal price;

}
