package com.booklidio.booklidio_spring_backend.Orders;

import java.sql.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.booklidio.booklidio_spring_backend.Inventory.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private ObjectId orderId;
    private List<Book> books;
    private ObjectId userId;
    private Date orderDate;
    private String address;

}
