package es.upm.dit.muirst.tfm.adapters;

import es.upm.dit.muirst.tfm.entities.UsuarioPostgres;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostgresAdapter {

    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    public UsuarioPostgres deleteUsuario( UsuarioPostgres usuarioPostgres){
        UsuarioPostgres.deleteById(usuarioPostgres.getDNI());
        return usuarioPostgres;
    }

}
