package es.upm.dit.muirst.tfm.mappers.deserializer;

import es.upm.dit.muirst.tfm.entities.UsuarioPostgres;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class UsuarioDeserializer extends ObjectMapperDeserializer<UsuarioPostgres> {

    public UsuarioDeserializer() {
        super(UsuarioPostgres.class);
    }
}
