package es.upm.dit.muirst.tfm;


import es.upm.dit.muirst.tfm.adapters.PostgresAdapter;
import es.upm.dit.muirst.tfm.entities.Usuario;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class msUpdate {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    private final Executor executor = Executors.newSingleThreadExecutor();


    @Inject
    PostgresAdapter adapter;


    @Incoming("usuarios-update")
    @Blocking
    @Transactional
    public void consumer(Usuario usuario){
        LOGGER.info("LLEGA: " + usuario);

        try{
            adapter.updateUsuario(usuario);
        }catch (Exception e){
            LOGGER.severe("No se ha podido actualizar dicho usuario");
        }



    }


}
