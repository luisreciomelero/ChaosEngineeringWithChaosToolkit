package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.entities.UsuarioPostgres;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioPostgresRepository implements PanacheRepository<UsuarioPostgres> {

    public UsuarioPostgres findByDni(String dni){
        return find("dni", dni).firstResult();
    }

    public List<UsuarioPostgres> listAll(){
        return listAll();
    }
}
