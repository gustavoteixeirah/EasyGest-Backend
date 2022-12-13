package dev.gustavoteixeira.easygest.application;


import dev.gustavoteixeira.easygest.model.product.NewProduct;
import dev.gustavoteixeira.easygest.model.product.Product;
import dev.gustavoteixeira.easygest.model.rating.NewRating;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import dev.gustavoteixeira.easygest.model.scheduling.NewScheduling;
import dev.gustavoteixeira.easygest.model.scheduling.Scheduling;
import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EasygestApplication {

    Mono<String> createNewService(Mono<NewService> service);

    Mono<String> createNewRating(Mono<NewRating> rating);

    Flux<Service> listServices();


    Mono<Service> update(Mono<Service> service);

    Mono<Void> delete(Mono<String> serviceId);

    Flux<Rating> listServiceRatings(String id);

    Flux<Rating> listRatings();

    Mono<String> createProduct(Mono<NewProduct> newProduct);

    Flux<Product> listProducts();

    Mono<Product> updateProduct(Mono<Product> product);

    Mono<Void> deleteProduct(Mono<String> productId);

    Mono<String> createScheduling(NewScheduling newScheduling);

    Flux<Scheduling> listSchedulings();

    Flux<Scheduling> listSchedulingsOfUser(Mono<String> username);

    Mono<Void> finishSchedule();

    Mono<Void> cancelSchedule();

}
