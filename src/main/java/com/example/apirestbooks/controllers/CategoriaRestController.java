package com.example.apirestbooks.controllers;

import com.example.apirestbooks.models.Categoria;
import com.example.apirestbooks.responses.categoria.CategoriaResponseRest;
import com.example.apirestbooks.services.categoria.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Categoria rest controller.
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
    @Autowired
    private ICategoriaService service;

    /**
     * Consultar cat categoria response rest.
     *
     * @return the categoria response rest
     */
    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultarCat() {
        return service.buscarCategorias();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request) {
        return service.crear(request);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria request, @PathVariable Long id) {
        return service.actualizar(request, id);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id) {
        return service.eliminar(id);
    }
}
