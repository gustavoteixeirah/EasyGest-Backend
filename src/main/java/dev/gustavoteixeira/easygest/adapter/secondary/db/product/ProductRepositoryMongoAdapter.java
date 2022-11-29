package dev.gustavoteixeira.easygest.adapter.secondary.db.product;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepositoryMongoAdapter extends ReactiveMongoRepository<ProductDocument, String> {


}