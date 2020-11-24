package org.acme.config;

import org.acme.config.adapters.kafka.ProducerKafka;
import org.acme.config.entities.Country;
import org.acme.config.entities.Usuario;
import org.acme.config.messages.UserMessage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Path("/api")
public class Mg {

    @ConfigProperty(name="kafka.bootstrap.servers")
    String host;


    @Inject @Channel("usuarios-create") Emitter<Usuario> createUsers;
    @Inject @Channel("usuarios-delete") Emitter<Usuario> deleteUsers;
    @Inject @Channel("usuarios-read") Emitter<Usuario> readUsers;

    private ProducerKafka producerKafka = new ProducerKafka();
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");


    public Mg() {

    }



    @POST
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Usuario usuario) {
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");
        LOGGER.info(usuario.toString());
        createUsers.send(usuario);
    }

    @DELETE
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(Usuario usuario) {
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");
        LOGGER.info(usuario.toString());
        deleteUsers.send(usuario);
    }


}
