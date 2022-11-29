package dev.gustavoteixeira.easygest.adapter.primary.http.product;

import dev.gustavoteixeira.easygest.application.EasygestApplication;
import dev.gustavoteixeira.easygest.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static reactor.core.publisher.Mono.just;
import static reactor.function.TupleUtils.function;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
class ProductHttpAdapter {

    private final EasygestApplication easygestApplication;
    private final ProductHttpMapper mapper;


    @PostMapping
    Mono<ResponseEntity<Void>> create(@RequestBody Mono<NewProductRequest> newProductRequest) {
        log.info("Request to create a new product received.");

        return newProductRequest.map(mapper::toNewProduct)
                .flatMap(newProduct -> easygestApplication.createProduct(just(newProduct)))
                .map(url -> ResponseEntity.created(URI.create(url)).build());
    }

    @GetMapping
    Flux<Product> list() {
        log.info("Request to list all products received.");

        return easygestApplication.listProducts();
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Product>> update(@PathVariable String id, @RequestBody Mono<UpdateProductRequest> updateProductRequest) {
        log.info("Request to update a existing product received.");

        return updateProductRequest.zipWith(just(id))
                .map(function(mapper::toProduct))
                .flatMap(newProduct -> easygestApplication.updateProduct(just(newProduct)))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("Request to delete a existing product received.");

        return easygestApplication.deleteProduct(just(id))
                .map(ResponseEntity::ok);
    }

}
