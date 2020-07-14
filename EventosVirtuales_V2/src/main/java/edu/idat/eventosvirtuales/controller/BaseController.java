package edu.idat.eventosvirtuales.controller;

import edu.idat.eventosvirtuales.utils.GenericResponse;

public interface BaseController<T, ID> {
    GenericResponse list();

    GenericResponse find(ID id);

    GenericResponse save(T obj);

    GenericResponse update(ID id, T obj);

    GenericResponse delete(ID id);
}
