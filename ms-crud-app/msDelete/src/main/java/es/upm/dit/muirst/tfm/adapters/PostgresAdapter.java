package es.upm.dit.muirst.tfm.adapters;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostgresAdapter {

    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    public Usuario deleteUsuario(Usuario usuario){
        Usuario.deleteById(usuario.getDNI());
        return usuario;
    }

}
