package es.upm.dit.muirst.tfm.utils.deserializers;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class UsuarioDeserializer extends JsonbDeserializer<Usuario> {

    public UsuarioDeserializer() {
        super(Usuario.class);
    }
}
