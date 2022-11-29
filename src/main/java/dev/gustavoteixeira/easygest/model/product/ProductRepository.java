package dev.gustavoteixeira.easygest.model.product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<String> create(Mono<NewProduct> newProduct);

    Flux<Product> list();

    Mono<Product> update(Mono<Product> product);

    Mono<Void> delete(Mono<String> productId);

}
