package edu.idat.eventosvirtuales.repository;

import edu.idat.eventosvirtuales.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.estado = 'A' AND u.eliminado = false")
    Iterable<Usuario> list();

    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password AND u.estado = 'A' AND u.eliminado = false")
    Optional<Usuario> findByCredenciales(String username, String password);

    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.eliminado = false")
    Optional<Usuario> findByUsername(String username);
}
