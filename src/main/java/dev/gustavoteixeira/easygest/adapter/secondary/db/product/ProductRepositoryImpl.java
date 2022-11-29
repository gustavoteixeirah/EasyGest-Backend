package dev.gustavoteixeira.easygest.adapter.secondary.db.product;

import dev.gustavoteixeira.easygest.model.product.NewProduct;
import dev.gustavoteixeira.easygest.model.product.Product;
import dev.gustavoteixeira.easygest.model.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
class ProductRepositoryImpl implements ProductRepository {

    private final ProductRepositoryMongoAdapter mongoAdapter;
    private final ProductMapper mapper;


    @Override
    public Mono<String> create(Mono<NewProduct> newProduct) {
        return newProduct.map(mapper::toProductDocument)
                .flatMap(mongoAdapter::save)
                .map(ProductDocument::getId);
    }

    @Override
    public Flux<Product> list() {
        return mongoAdapter.findAll()
                .map(mapper::toProduct);
    }

    @Override
    public Mono<Product> update(Mono<Product> product) {
        return product.map(mapper::toProductDocument)
                .flatMap(mongoAdapter::save)
                .map(mapper::toProduct);
    }

    @Override
    public Mono<Void> delete(Mono<String> productId) {
        return mongoAdapter.findById(productId)
                .flatMap(mongoAdapter::delete)
                .then();
    }
}
