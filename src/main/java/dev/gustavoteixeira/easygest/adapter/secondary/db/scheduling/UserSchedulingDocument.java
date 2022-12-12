package dev.gustavoteixeira.easygest.adapter.secondary.db.scheduling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
class UserSchedulingDocument {

    String id;

    String fullName;

    String username;

    String email;

    String cnpj;

    boolean active;

    List<String> roles;

}
