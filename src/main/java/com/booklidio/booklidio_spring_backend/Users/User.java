package com.booklidio.booklidio_spring_backend.Users;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId userId;
    private String firstName;
    private String lastName;
    private String email;
    private String cellphone;
    private Boolean allowsMarketing;
    private Boolean isBuyer;
    private Boolean isSeller;

    public String getUserId() {
        return userId.toHexString();
    }

    public void setUserId(String id) {
        this.userId = new ObjectId(id);
    }

}
