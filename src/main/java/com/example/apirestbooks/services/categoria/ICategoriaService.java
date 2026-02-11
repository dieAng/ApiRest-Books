package com.example.apirestbooks.services.categoria;

import com.example.apirestbooks.models.Categoria;
import com.example.apirestbooks.responses.categoria.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

/**
 * The interface Categoria service.
 */
public interface ICategoriaService {
    /**
     * Buscar categorias categoria response rest.
     *
     * @return the categoria response rest
     */
    public ResponseEntity<CategoriaResponseRest> buscarCategorias();

    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);

    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);

    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);

    public  ResponseEntity<CategoriaResponseRest> eliminar(Long id);
}
