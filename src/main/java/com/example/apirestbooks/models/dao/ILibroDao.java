package com.example.apirestbooks.models.dao;

import com.example.apirestbooks.models.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro, Long> {

}
