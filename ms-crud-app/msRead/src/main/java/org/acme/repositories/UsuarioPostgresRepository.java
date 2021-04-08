package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.entities.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioPostgresRepository implements PanacheRepository<Usuario> {

    public Usuario findByDni(String dni){
        return find("dni", dni).firstResult();
    }

    public List<Usuario> listAll(){
        return listAll();
    }
}
