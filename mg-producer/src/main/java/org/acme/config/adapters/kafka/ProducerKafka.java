package org.acme.config.adapters.kafka;


import org.acme.config.entities.Usuario;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerKafka {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    private Random random = new Random();

    public ProducerKafka(){

    }

    @Inject @Channel("usuarios") Emitter<Usuario> publisherUsers;
    public void generate(Usuario usuario) {
        publisherUsers.send(usuario);
    }


}
