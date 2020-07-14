package edu.idat.eventosvirtuales.repository;

import edu.idat.eventosvirtuales.entity.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Long> {
    @Query("SELECT ur.rol FROM UsuarioRol ur WHERE ur.usuario.username = :username")
    Iterable<Rol> findByUsername(String username);
}
