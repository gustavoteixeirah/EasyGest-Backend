package dev.gustavoteixeira.easygest.adapter.secondary.db.service;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ServiceRepositoryMongoAdapter extends ReactiveMongoRepository<ServiceDocument, String> {


}
