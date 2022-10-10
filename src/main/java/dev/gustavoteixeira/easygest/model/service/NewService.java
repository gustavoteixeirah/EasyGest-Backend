package dev.gustavoteixeira.easygest.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class NewService {

    String description;

    String price;

    int durationInMinutes;

}
