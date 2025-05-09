package com.booklidio.booklidio_spring_backend.Inventory;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, ObjectId> {

    Optional<Book> findByIsbn(String isbn);

}