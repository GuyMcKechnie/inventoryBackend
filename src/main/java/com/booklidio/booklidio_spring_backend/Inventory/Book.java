package com.booklidio.booklidio_spring_backend.Inventory;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @JsonIgnore
    private ObjectId id;
    private String isbn;
    private String title;
    private int grade;
    private double newPrice;
    private double usedPrice;
    private double costPrice;

}
