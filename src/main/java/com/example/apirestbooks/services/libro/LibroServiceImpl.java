package com.example.apirestbooks.services.libro;

import com.example.apirestbooks.models.Libro;
import com.example.apirestbooks.models.dao.ILibroDao;
import com.example.apirestbooks.responses.libro.LibroResponseRest;
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

@Service
public class LibroServiceImpl implements ILibroService {
    private static final Logger LOG = LoggerFactory.getLogger(LibroServiceImpl.class);
    @Autowired
    private ILibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        LOG.info("Buscando libros");

        LibroResponseRest response = new LibroResponseRest();

        try {
            List<Libro> libros = (List<Libro>) libroDao.findAll();
            response.getLibroResponse().setLibros(libros);
            response.setMetadata("Ok", "00", "Exito al buscar libros");

        } catch (Exception e) {
            response.setMetadata("Nok", "-1", "Error al buscar libros");
            LOG.error("Error al consultar libros: {}", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
        LOG.info("Buscando libro por ID {}", id);

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> libros = new ArrayList<>();

        try {
            Optional<Libro> libro = libroDao.findById(id);

            if (libro.isPresent()) {
                libros.add(libro.get());
                response.getLibroResponse().setLibros(libros);
                response.setMetadata("Ok", "00", "Exito al buscar libro");

            } else {
                response.setMetadata("Nok", "-1", "Error al buscar libro");
                LOG.error("Error al consultar el libro {}", id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Nok", "-1", "Error al buscar libro");
            LOG.error("Error al consultar el libro {}: {}", id, e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> crear(Libro libro) {
        LOG.info("Creando libro {}", libro);

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> libros = new ArrayList<>();

        try {
            Libro libroGuardado = libroDao.save(libro);

            if (libroGuardado != null) {
                libros.add(libroGuardado);
                response.getLibroResponse().setLibros(libros);
                response.setMetadata("Ok", "00", "Exito al crear libro");

            } else {
                LOG.error("Error al crear el libro {}", libro);
                response.setMetadata("Nok", "-1", "Error al crear libro");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            LOG.error("Error al crear el libro {}: {}", libro, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al crear libro");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {
        LOG.info("Actualizando libro {}", libro);

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> libros = new ArrayList<>();

        try {
            Optional<Libro> libroBuscado = libroDao.findById(id);

            if (libroBuscado.isPresent()) {
                libroBuscado.get().setTitulo(libro.getTitulo());
                libroBuscado.get().setDescripcion(libro.getDescripcion());
                libroBuscado.get().setCategoria(libro.getCategoria());

                Libro libroActualizado = libroDao.save(libroBuscado.get());

                if (libroActualizado != null) {
                    libros.add(libroActualizado);
                    response.getLibroResponse().setLibros(libros);
                    response.setMetadata("Ok", "00", "Exito al actualizar libro");

                } else {
                    LOG.error("Error al actualizar el libro {}", libro);
                    response.setMetadata("Nok", "-1", "Error al actualizar libro");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                LOG.error("Error al actualizar el libro {}", libro);
                response.setMetadata("Nok", "-1", "Error al actualizar libro");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al actualizar el libro {}: {}", libro, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al actualizar libro");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> eliminar(Long id) {
        LOG.info("Eliminando libro {}", id);

        LibroResponseRest response = new LibroResponseRest();

        try {
            libroDao.deleteById(id);
            response.setMetadata("Ok", "00", "Exito al eliminar libro");

        } catch (Exception e) {
            LOG.error("Error al eliminar el libro {}: {}", id, e.getMessage());
            response.setMetadata("Nok", "-1", "Error al eliminar libro");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
