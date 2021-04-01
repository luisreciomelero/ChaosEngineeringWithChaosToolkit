package org.acme;

import io.quarkus.panache.common.Sort;
import org.acme.entities.UsuarioPostgres;
import org.acme.repositories.UsuarioPostgresRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/api")
public class Service {

    @Inject
    UsuarioPostgresRepository usuarioPostgresRepository;

    @GET
    @Path("/usuarios")
    public List<UsuarioPostgres> getAll() {
        return usuarioPostgresRepository.listAll(Sort.by("dni"));
    }

    @GET
    @Path("/usuarios/{dni}")
    public UsuarioPostgres getByDni(@PathParam("dni") String dni) {
        return usuarioPostgresRepository.findByDni(dni);
    }



}