package es.upm.dit.muirst.tfm;

import es.upm.dit.muirst.tfm.entities.Usuario;
import es.upm.dit.muirst.tfm.services.Service;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

public class msSender {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    @Inject
    Service service;

    @Incoming("usuarios-sendsms")
    @Blocking
    @Transactional
    public void consumer(Usuario usuario){
        LOGGER.info("LLEGA: " + usuario);

        try{
            service.sendSMS(usuario);

        }catch (Exception e){
            LOGGER.severe("Problema al enviar: " + e);
        }



    }


}
