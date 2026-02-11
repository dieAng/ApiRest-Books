package com.example.apirestbooks.services;

import com.example.apirestbooks.models.Categoria;
import com.example.apirestbooks.models.dao.ICategoriaDao;
import com.example.apirestbooks.responses.categoria.CategoriaResponseRest;
import com.example.apirestbooks.services.categoria.CaregoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoriaServiceImplTest {
    @InjectMocks
    CaregoriaServiceImpl service;
    @Mock
    ICategoriaDao categoriaDao;
    List<Categoria> categorias = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        this.cargarCategorias();
    }

    @Test
    void buscarCategoriasTest() {
        when(categoriaDao.findAll()).thenReturn(categorias);

        ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();

        assertEquals(4, response.getBody().getCategoriaResponse().getCategorias().size());
        verify(categoriaDao, times(1)).findAll();
    }


    public void cargarCategorias() {
        Categoria categoria1 = new Categoria(1L, "Abarrotes", "Distintos abarrotes");
        Categoria categoria2 = new Categoria(1L, "Lacteos", "Distintos lacteos");
        Categoria categoria3 = new Categoria(1L, "Bebidas", "Bebidas sin azucar");
        Categoria categoria4 = new Categoria(1L, "Carnes blancas", "Distintas carnes blancas");

        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
    }
}
