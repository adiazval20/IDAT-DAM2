package edu.idat.eventosvirtuales.service;

import edu.idat.eventosvirtuales.utils.GenericResponse;

import java.util.HashMap;

public interface BaseService<T, ID> {
    GenericResponse list();

    GenericResponse find(ID id);

    GenericResponse save(T obj);

    GenericResponse delete(ID id);

    HashMap<String, Object> validate(T obj);
}
