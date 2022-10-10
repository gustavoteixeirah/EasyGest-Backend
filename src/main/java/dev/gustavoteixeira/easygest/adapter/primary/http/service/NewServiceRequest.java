package dev.gustavoteixeira.easygest.adapter.primary.http.service;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class NewServiceRequest {

    String description;

    String price;

    int durationInMinutes;

}
