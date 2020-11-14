package org.acme.commandmode;


import es.upm.dit.muirst.tfm.adapters.PostgresAdapter;
import es.upm.dit.muirst.tfm.entities.Country;
import es.upm.dit.muirst.tfm.entities.CountryPostgres;
import es.upm.dit.muirst.tfm.resources.CountryResource;
import es.upm.dit.muirst.tfm.resources.CountryResourceMongoDB;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.impl.VertxThread;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class msConsumer  {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
   /* @Inject
    CountryResource countryResource;*/

    private final Executor executor = Executors.newSingleThreadExecutor();


    @Inject
    PostgresAdapter adapter;
    //EntityManager entityManager;


    @Incoming("test")
    @Blocking
    @Transactional
    public void consumer(CountryPostgres country){
        LOGGER.info("LLEGA: " + country);

        try{
            adapter.persistCountry(country);
        }catch (Exception e){
            LOGGER.severe(e.getMessage());
        }



    }


}
