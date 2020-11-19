package org.acme.config;

import org.acme.config.adapters.kafka.ProducerKafka;
import org.acme.config.entities.Country;
import org.acme.config.entities.Usuario;
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

    @Inject @Channel("test") Emitter<Country> publisher;
    @Inject @Channel("usuarios") Emitter<Usuario> publisherUsers;

    private ProducerKafka producerKafka = new ProducerKafka();
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");


    public Mg() {

    }


    @POST
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Country country) {
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");

        publisher.send(country);
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
        publisherUsers.send(usuario);
    }


}
