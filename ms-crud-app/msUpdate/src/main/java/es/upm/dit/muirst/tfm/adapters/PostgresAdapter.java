package es.upm.dit.muirst.tfm.adapters;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostgresAdapter {



    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    public void updateUsuario( Usuario usuario){
        Usuario user = Usuario.findById(usuario.getDNI());

        if(user!=null){
            user.setEstadoNofif(usuario.getEstadoNofif());
        }

    }

}
