package com.wrwp.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.wrwp.spring5webapp.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
