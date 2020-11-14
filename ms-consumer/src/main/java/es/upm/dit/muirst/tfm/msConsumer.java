package es.upm.dit.muirst.tfm;


import es.upm.dit.muirst.tfm.adapters.PostgresAdapter;
import es.upm.dit.muirst.tfm.entities.CountryPostgres;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class msConsumer  {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    private final Executor executor = Executors.newSingleThreadExecutor();


    @Inject
    PostgresAdapter adapter;

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
