package dev.gustavoteixeira.easygest.adapter.primary.http.product;

import dev.gustavoteixeira.easygest.model.product.NewProduct;
import dev.gustavoteixeira.easygest.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface ProductHttpMapper {

    NewProduct toNewProduct(NewProductRequest newProductRequest);

    @Mapping(target = "id", source = "id")
    Product toProduct(UpdateProductRequest updateProductRequest, String id);

}
