package es.upm.dit.muirst.tfm;

import es.upm.dit.muirst.tfm.entities.Usuario;
import es.upm.dit.muirst.tfm.services.Service;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

public class msMailer {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    @Inject
    Service service;



    @Incoming("usuarios-sendemail")
    @Blocking
    @Transactional
    public void consumer(Usuario usuario){
        LOGGER.info("LLEGA: " + usuario);

        try{
            service.sendMail(usuario);

        }catch (Exception e){
            LOGGER.severe("Problema al enviar: " + e);
        }



    }


}
