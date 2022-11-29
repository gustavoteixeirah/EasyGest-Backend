package dev.gustavoteixeira.easygest.adapter.secondary.db.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@Document("Products")
class ProductDocument {

    @Id
    String id;

    String description;

    BigDecimal price;

}
