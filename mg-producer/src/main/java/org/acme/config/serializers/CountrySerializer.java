package org.acme.config.serializers;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.config.entities.Country;

public class CountrySerializer extends ObjectMapperDeserializer<Country> {
    public CountrySerializer(){
        // pass the class to the parent.
        super(Country.class);
    }
}
