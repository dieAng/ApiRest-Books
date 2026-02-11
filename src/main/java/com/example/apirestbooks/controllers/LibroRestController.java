package com.example.apirestbooks.controllers;

import com.example.apirestbooks.models.Libro;
import com.example.apirestbooks.responses.libro.LibroResponseRest;
import com.example.apirestbooks.services.libro.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LibroRestController {
    @Autowired
    private ILibroService service;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> consultarLib() {
        return service.buscarLibros();
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> consultarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/libros")
    public ResponseEntity<LibroResponseRest> crear(@RequestBody Libro request) {
        return service.crear(request);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> actualizar(@RequestBody Libro request, @PathVariable Long id) {
        return service.actualizar(request, id);
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> eliminar(@PathVariable Long id) {
        return service.eliminar(id);
    }
}
