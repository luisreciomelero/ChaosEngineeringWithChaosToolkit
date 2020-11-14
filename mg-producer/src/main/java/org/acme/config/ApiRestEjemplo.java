package org.acme.config;

import org.acme.config.adapters.kafka.ProducerKafka;
import org.acme.config.entities.Country;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Path("/v2")
public class ApiRestEjemplo {

    @Inject @Channel("test") Emitter<Country> publisher;

    private ProducerKafka producerKafka = new ProducerKafka();
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    private Set<Country> countries = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public ApiRestEjemplo() {
       countries.add(new Country("España","34","Madrid"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Country> list() {
        return countries;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Country> add(Country country) {
        countries.add(country);
        //producerKafka.generate(country);
        /*
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(country);
        publisher.send(result);*/
        publisher.send(country);
        return countries;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Country> delete(Country country) {
        countries.removeIf(existingCountry -> existingCountry.getName().contentEquals(country.getName()));
        return countries;
    }
}
