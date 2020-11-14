package org.acme.config;

import org.acme.config.adapters.kafka.ProducerKafka;
import org.acme.config.entities.Country;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/country")
public class Mg {

    @Inject @Channel("test") Emitter<Country> publisher;

    private ProducerKafka producerKafka = new ProducerKafka();
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");


    public Mg() {

    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Country country) {
        publisher.send(country);
    }


}
