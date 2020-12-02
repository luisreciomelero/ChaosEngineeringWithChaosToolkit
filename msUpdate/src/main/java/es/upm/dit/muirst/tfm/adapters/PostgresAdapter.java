package es.upm.dit.muirst.tfm.adapters;

import es.upm.dit.muirst.tfm.entities.UsuarioPostgres;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostgresAdapter {

    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    public void updateUsuario( UsuarioPostgres usuarioPostgres){
        UsuarioPostgres user = UsuarioPostgres.findById(usuarioPostgres.getDNI());
        if(user!=null){
            user.setEstadoNofif(usuarioPostgres.getEstadoNofif());
        }

    }

}
