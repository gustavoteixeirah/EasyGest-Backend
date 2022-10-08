package dev.gustavoteixeira.easygest.adapter.primary.http.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class AuthenticationRequest {

    String username;

    String password;

}