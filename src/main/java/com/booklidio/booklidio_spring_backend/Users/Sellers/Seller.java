package com.booklidio.booklidio_spring_backend.Users.Sellers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.booklidio.booklidio_spring_backend.Inventory.Book;
import com.booklidio.booklidio_spring_backend.Users.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sellers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seller extends User {
    private ObjectId sellerId;
    private ObjectId userId;
    private List<Book> books;
}
