package com.booklidio.booklidio_spring_backend.Inventory;

import lombok.Data;

@Data
public class BookDTO {
    private String isbn;
    private String title;
    private int grade;
    private double newPrice;
    private double usedPrice;
    private double costPrice;
}