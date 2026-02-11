package com.example.apirestbooks.services.categoria;

import com.example.apirestbooks.models.Categoria;
import com.example.apirestbooks.models.dao.ICategoriaDao;
import com.example.apirestbooks.responses.categoria.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Caregoria service.
 */
@Service
public class CaregoriaServiceImpl implements ICategoriaService {
    private static final Logger LOG = LoggerFactory.getLogger(CaregoriaServiceImpl.class);
    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        LOG.info("Buscando categorias");

        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(categorias);
            response.setMetadata("Ok", "00", "Exito al buscar categorias");

        } catch (Exception e) {
            response.setMetadata("Nok", "-1", "Error al buscar categorias");
            LOG.error("Error al consultar categorías: {}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
        LOG.info("Buscando categoria por ID {}", id);

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()) {
                categorias.add(categoria.get());
                response.getCategoriaResponse().setCategorias(categorias);
                response.setMetadata("Ok", "00", "Exito al buscar categoria");

            } else {
                response.setMetadata("Nok", "-1", "Error al buscar categoria");
                LOG.error("Error al consultar la categoría {}", id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al consultar la categoría {}: {}", id, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al buscar categoria");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
        LOG.info("Creando categoria {}", categoria);

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Categoria categoriaGuardada = categoriaDao.save(categoria);

            if (categoriaGuardada != null) {
                categorias.add(categoriaGuardada);
                response.getCategoriaResponse().setCategorias(categorias);
                response.setMetadata("Ok", "00", "Exito al crear categoría");

            } else {
                LOG.error("Error al crear la categoria {}", categoria);
                response.setMetadata("Nok", "-1", "Error al crear categoría");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            LOG.error("Error al crear la categoria {}: {}", categoria, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al crear categoría");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
        LOG.info("Actualizando categoria {}", categoria);

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);

            if (categoriaBuscada.isPresent()) {
                categoriaBuscada.get().setNombre(categoria.getNombre());
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion());

                Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get());

                if (categoriaActualizada != null) {
                    categorias.add(categoriaActualizada);
                    response.getCategoriaResponse().setCategorias(categorias);
                    response.setMetadata("Ok", "00", "Exito al actualizar categoria");

                } else {
                    LOG.error("Error al actualizar la categoria {}", categoria);
                    response.setMetadata("Nok", "-1", "Error al actualizar categoria");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                LOG.error("Error al actualizar la categoria {}", categoria);
                response.setMetadata("Nok", "-1", "Error al actualizar categoria");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al actualizar la categoria {}: {}", categoria, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al actualizar categoria");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        LOG.info("Eliminando categoria {}", id);

        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            categoriaDao.deleteById(id);
            response.setMetadata("Ok", "00", "Exito al eliminar categoria");

        } catch (Exception e) {
            LOG.error("Error al eliminar la categoria {}: {}", id, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al eliminar categoria");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
