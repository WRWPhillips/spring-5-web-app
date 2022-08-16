package com.wrwp.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.wrwp.spring5webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
