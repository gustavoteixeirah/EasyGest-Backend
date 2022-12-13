package dev.gustavoteixeira.easygest.application;

import dev.gustavoteixeira.easygest.model.product.NewProduct;
import dev.gustavoteixeira.easygest.model.product.Product;
import dev.gustavoteixeira.easygest.model.product.ProductRepository;
import dev.gustavoteixeira.easygest.model.rating.NewRating;
import dev.gustavoteixeira.easygest.model.rating.Rating;
import dev.gustavoteixeira.easygest.model.rating.RatingRepository;
import dev.gustavoteixeira.easygest.model.scheduling.NewScheduling;
import dev.gustavoteixeira.easygest.model.scheduling.Scheduling;
import dev.gustavoteixeira.easygest.model.scheduling.SchedulingRepository;
import dev.gustavoteixeira.easygest.model.service.NewService;
import dev.gustavoteixeira.easygest.model.service.Service;
import dev.gustavoteixeira.easygest.model.service.ServiceRepository;
import dev.gustavoteixeira.easygest.model.user.User;
import dev.gustavoteixeira.easygest.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasygestApplicationImpl implements EasygestApplication {

    private final ServiceRepository serviceRepository;
    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final SchedulingRepository schedulingRepository;
    private final UserRepository userRepository;

    @Override
    public Mono<String> createNewService(Mono<NewService> service) {
        return serviceRepository.create(service);
    }

    @Override
    public Mono<String> createNewRating(Mono<NewRating> newRating) {
        return ratingRepository.rate(newRating);
    }

    @Override
    public Flux<Service> listServices() {
        return serviceRepository.list();
    }

    @Override
    public Mono<Service> update(Mono<Service> service) {
        return serviceRepository.update(service);
    }

    @Override
    public Mono<Void> delete(Mono<String> serviceId) {
        return serviceRepository.delete(serviceId)
                .then();
    }

    @Override
    public Flux<Rating> listServiceRatings(String id) {
        return ratingRepository.findAllRatesOfService(Mono.just(id));
    }

    @Override
    public Flux<Rating> listRatings() {
        return ratingRepository.list();
    }

    @Override
    public Mono<String> createProduct(Mono<NewProduct> newProduct) {
        return productRepository.create(newProduct);
    }

    @Override
    public Flux<Product> listProducts() {
        return productRepository.list();
    }

    @Override
    public Mono<Product> updateProduct(Mono<Product> product) {
        return productRepository.update(product);
    }

    @Override
    public Mono<Void> deleteProduct(Mono<String> productId) {
        return productRepository.delete(productId);
    }

    @Override
    public Mono<String> createScheduling(NewScheduling newScheduling) {
        Mono<User> user = Mono.just(newScheduling)
                .map(NewScheduling::getCustomerId)
                .flatMap(userRepository::findById);

        Mono<List<Service>> services = Mono.just(newScheduling)
                .map(NewScheduling::getServicesId)
                .flatMapMany(Flux::fromIterable)
                .flatMap(serviceRepository::findById)
                .collectList();

        Mono<Scheduling> scheduling = user.zipWith(services)
                .map(objects -> Scheduling.builder()
                        .services(objects.getT2())
                        .customer(objects.getT1())
                        .status(newScheduling.getStatus())
                        .dateTime(newScheduling.getDateTime())
                        .build());

        return schedulingRepository.create(scheduling);
    }

    @Override
    public Flux<Scheduling> listSchedulings() {
        return schedulingRepository.list();
    }

    @Override
    public Flux<Scheduling> listSchedulingsOfUser(Mono<String> username) {
        return schedulingRepository.listFrom(username);
    }

    @Override
    public Mono<Void> finishSchedule() {
        return null;
    }

    @Override
    public Mono<Void> cancelSchedule() {
        return null;
    }


}
