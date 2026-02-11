package com.example.apirestbooks.responses.categoria;

import com.example.apirestbooks.responses.ResponseRest;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Categoria response rest.
 */
@Setter
@Getter
public class CategoriaResponseRest extends ResponseRest {
    private CategoriaResponse categoriaResponse = new CategoriaResponse();
}
