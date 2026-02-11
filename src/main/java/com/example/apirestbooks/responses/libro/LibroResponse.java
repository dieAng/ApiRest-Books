package com.example.apirestbooks.responses.libro;

import com.example.apirestbooks.models.Libro;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LibroResponse {
    private List<Libro> libros;
}
