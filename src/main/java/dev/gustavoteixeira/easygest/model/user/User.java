package dev.gustavoteixeira.easygest.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
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

    String username;

    @JsonIgnore
    String password;

    String email;

    @Builder.Default()
    boolean active = true;

    @Builder.Default()
    List<String> roles = new ArrayList<>();

}
