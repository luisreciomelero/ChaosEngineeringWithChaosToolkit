package org.acme.config.serializers;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.config.entities.Usuario;

public class UsuarioDeserializer extends ObjectMapperDeserializer<Usuario> {

    public UsuarioDeserializer() {
        super(Usuario.class);
    }
}
