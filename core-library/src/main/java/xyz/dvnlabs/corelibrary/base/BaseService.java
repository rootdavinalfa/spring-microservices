package xyz.dvnlabs.corelibrary.base;

import xyz.dvnlabs.corelibrary.exception.ResourceExistException;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID> {

    E findById(ID id);

    List<E> findAll();

    E create(E entity) throws ResourceExistException;

    E update(E entity);

    void delete(ID id);
}
