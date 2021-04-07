package es.upm.dit.muirst.tfm.adapters;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostgresAdapter {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");


    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    public Usuario persistUsuario( Usuario usuarioPostgres){
        Usuario user = Usuario.findById(usuarioPostgres.getDNI());
        if(user!=null){
            LOGGER.info("El usuario ya existe en el sistema");
            return user;
        }else{
            Usuario.persist(usuarioPostgres);
            return usuarioPostgres;
        }

    }

}
