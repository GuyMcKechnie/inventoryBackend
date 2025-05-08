package com.booklidio.booklidio_spring_backend.Users;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @JsonIgnore
    private ObjectId id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String cellphone;
    private Boolean allowsMarketing;
    private Boolean isBuyer;
    private Boolean isSeller;

}
