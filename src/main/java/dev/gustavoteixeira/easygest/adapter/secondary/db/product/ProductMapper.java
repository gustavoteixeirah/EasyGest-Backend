package dev.gustavoteixeira.easygest.adapter.secondary.db.product;

import dev.gustavoteixeira.easygest.model.product.NewProduct;
import dev.gustavoteixeira.easygest.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface ProductMapper {

    Product toProduct(ProductDocument productDocument);

    @Mapping(target = "id", ignore = true)
    ProductDocument toProductDocument(NewProduct newProduct);

    ProductDocument toProductDocument(Product product);

}
