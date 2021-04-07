package es.upm.dit.muirst.tfm.mappers.deserializer;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class UsuarioDeserializer extends ObjectMapperDeserializer<Usuario> {

    public UsuarioDeserializer() {
        super(Usuario.class);
    }
}
