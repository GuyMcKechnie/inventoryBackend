package com.booklidio.booklidio_spring_backend.Users.Buyers;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.booklidio.booklidio_spring_backend.Users.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "buyers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Buyer extends User {
    @Id
    private ObjectId buyerId;
    private ObjectId userId;
    private ObjectId orderId;
}
