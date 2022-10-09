package dev.gustavoteixeira.easygest.adapter.secondary.db.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@Document("Services")
class ServiceDocument {

    @Id
    String id;

    String description;

    String price;

    int durationInMinutes;


}
