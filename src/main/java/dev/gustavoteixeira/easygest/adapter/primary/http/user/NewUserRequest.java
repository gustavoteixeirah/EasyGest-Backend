package dev.gustavoteixeira.easygest.adapter.primary.http.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class NewUserRequest {

    String username;

    String password;

    String email;

}
