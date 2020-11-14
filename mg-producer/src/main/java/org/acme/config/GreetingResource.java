package org.acme.config;


import org.acme.config.adapters.kafka.ProducerKafka;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.logging.Logger;

@Path("/greeting")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.suffix", defaultValue="!")
    String suffix;

    @ConfigProperty(name = "greeting.name")
    Optional<String> name;

    private ProducerKafka producerKafka;
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("PRUEBA INFO");
        return message + " " + name.orElse("world") + suffix;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String post(){
        //producerKafka.generate();
        return "Se ha realizado el post";
    }

}