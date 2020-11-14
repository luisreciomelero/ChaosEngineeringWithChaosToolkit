package es.upm.dit.muirst.tfm.mappers.deserializer;

import es.upm.dit.muirst.tfm.entities.Country;
import es.upm.dit.muirst.tfm.entities.CountryPostgres;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class CountryDeserializer extends ObjectMapperDeserializer<CountryPostgres> {
    public CountryDeserializer(){
        // pass the class to the parent.
        super(CountryPostgres.class);
    }
}
