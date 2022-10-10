package dev.gustavoteixeira.easygest.adapter.primary.http.service;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UpdateServiceRequest {

    String id;

    String description;

    String price;

    int durationInMinutes;

}
