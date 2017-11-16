package studio.limo.web.blog.core.service;


import studio.limo.web.blog.core.exception.ResourceAlreadyExistException;
import studio.limo.web.blog.core.exception.ResourceNotFoundException;

import java.io.Serializable;

public interface GenericEntityService<T, ID extends Serializable> {

    <S extends T> void save(S s) throws ResourceAlreadyExistException;

    <S extends T> void update(S s) throws ResourceNotFoundException;

    T findById(ID id);

    void delete(ID id);
}
