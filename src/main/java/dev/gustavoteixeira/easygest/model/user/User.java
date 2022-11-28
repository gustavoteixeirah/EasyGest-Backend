package dev.gustavoteixeira.easygest.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@Document(collection = "Users")
public class User {

    @Id
    String id;

    String fullName;

    @Indexed(unique = true)
    String username;

    @JsonIgnore
    String password;

    @Indexed(unique = true)
    String email;

    @Indexed(unique = true)
    String cnpj;

    @Builder.Default()
    boolean active = true;

    @Builder.Default()
    List<String> roles = new ArrayList<>();

}
