package com.example.apirestbooks.responses.categoria;

import com.example.apirestbooks.models.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Categoria response.
 */
@Setter
@Getter
public class CategoriaResponse {
    private List<Categoria> categorias;
}
