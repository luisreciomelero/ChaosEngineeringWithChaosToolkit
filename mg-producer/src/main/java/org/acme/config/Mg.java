package org.acme.config;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import org.acme.config.adapters.kafka.ProducerKafka;
import org.acme.config.entities.Country;
import org.acme.config.entities.Usuario;
import org.acme.config.messages.UserMessage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Path("/api")
public class Mg {

    @ConfigProperty(name="kafka.bootstrap.servers")
    String host;

    @Inject
    EventBus bus;
    @ConfigProperty(name="msRead.address") String readAddres;

    @Inject @Channel("usuarios-create") Emitter<Usuario> createUsers;
    @Inject @Channel("usuarios-delete") Emitter<Usuario> deleteUsers;
    @Inject @Channel("usuarios-read") Emitter<Usuario> readUsers;
    @Inject @Channel("usuarios-update") Emitter<Usuario> updateUsers;
    @Inject @Channel("usuarios-sendEmail") Emitter<Usuario> sendEmailUsers;

    private ProducerKafka producerKafka = new ProducerKafka();
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");


    public Mg() {

    }



    @POST
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Usuario usuario) {
        LOGGER.info("POST");
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");
        LOGGER.info(usuario.toString());
        createUsers.send(usuario);
    }
//////////////////////////////////////////////////////////////////
    @POST
    @Path("/usuarios/send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void send(Usuario usuario) {
        LOGGER.info("POST");
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");
        LOGGER.info(usuario.toString());
        sendEmailUsers.send(usuario);
    }
///////////////////////////////////////////////////////////////////////
    @DELETE
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(Usuario usuario) {
        LOGGER.info("DELETE");
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");
        LOGGER.info(usuario.toString());
        deleteUsers.send(usuario);
    }

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<String> get(Usuario usuario) {
        LOGGER.info("GET");
        /*LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");*/
        LOGGER.info(usuario.toString());
        //readUsers.send(usuario);
        LOGGER.info("address: "+ readAddres);
        return bus.<String>request(readAddres, usuario).onItem().transform(Message::body);
    }

    @PUT
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Usuario usuario) {
        LOGGER.info("PUT");
        LOGGER.info("-----------------------------------------");
        LOGGER.info(System.getenv("KAFKA_HOST"));
        LOGGER.info("-----------------------------------------");
        LOGGER.info("EL HOST AL QUE APUNTAMOS ES: " + host);
        LOGGER.info("-----------------------------------------");
        LOGGER.info(usuario.toString());
        updateUsers.send(usuario);
        LOGGER.info("address: "+ readAddres);
        //return bus.<String>request(readAddres, usuario).onItem().transform(Message::body);
    }

    @Incoming("sendEmail-usuarios")
    public Usuario incomingUser(Usuario usuario){
        LOGGER.info("regresa a sendEmail-usuarios: " + usuario);
        String canalPrefer = usuario.getCanalPrefer();
        String canalContac = usuario.getCanalContac();
        String estadoNotif = usuario.getEstadoNofif();
        if(!canalPrefer.equals(canalContac)){
            LOGGER.info("Se tendrá que enviar al topic de: "+canalContac);
        }else if(estadoNotif != null && estadoNotif.contains("Enviado")){
            LOGGER.info("Procedemos a actualizar el usuario");
            updateUsers.send(usuario);

        }else{
            LOGGER.info("Se tendrá que enviar al topic de: "+canalContac);
            String canal = canalPrefer.toLowerCase();
            switch (canal){
                case "email":
                    //sendEmailUsers.send(usuario);
                    break;
                case "telefono":
                    LOGGER.info("ENVIAR A MSTELEFONO");

            }
        }
        return usuario;
    }



}
