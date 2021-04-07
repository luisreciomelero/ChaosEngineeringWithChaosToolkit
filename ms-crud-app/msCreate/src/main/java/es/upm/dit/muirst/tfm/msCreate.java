package es.upm.dit.muirst.tfm;


import es.upm.dit.muirst.tfm.adapters.PostgresAdapter;
import es.upm.dit.muirst.tfm.entities.Usuario;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;


public class msCreate {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");


    @Inject
    PostgresAdapter adapter;

    @Inject @Channel("usuarios-sendnotification")
    Emitter<Usuario> sendNotification;


    @Incoming("usuarios-create")
    @Blocking
    @Transactional
    public void consumer(Usuario usuario){
        LOGGER.info("LLEGA: " + usuario);

        try{
            Usuario user = adapter.persistUsuario(usuario);
            sendNotification.send(user);
        }catch (Exception e){
            LOGGER.severe("Usuario ya registrado");
        }



    }


}
