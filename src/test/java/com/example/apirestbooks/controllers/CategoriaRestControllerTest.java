package com.example.apirestbooks.controllers;

import com.example.apirestbooks.models.Categoria;
import com.example.apirestbooks.responses.categoria.CategoriaResponseRest;
import com.example.apirestbooks.services.categoria.ICategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoriaRestControllerTest {
    @InjectMocks
    CategoriaRestController categoriaController;
    @Mock
    ICategoriaService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria = new Categoria(1L, "Clásicos", "Libros clásicos de la literatura");

        when(service.crear(any(Categoria.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<CategoriaResponseRest> response = categoriaController.crear(categoria);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
