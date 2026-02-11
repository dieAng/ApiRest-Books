package com.example.apirestbooks.services.libro;

import com.example.apirestbooks.models.Libro;
import com.example.apirestbooks.responses.libro.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {
    public ResponseEntity<LibroResponseRest> buscarLibros();

    public ResponseEntity<LibroResponseRest> buscarPorId(Long id);

    public ResponseEntity<LibroResponseRest> crear(Libro libro);

    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id);

    public  ResponseEntity<LibroResponseRest> eliminar(Long id);
}
