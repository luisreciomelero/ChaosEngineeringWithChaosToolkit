package org.acme.config;

import org.acme.config.entities.Usuario;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Locale;
import java.util.logging.Logger;

@Path("/api")
public class Mg {

    @ConfigProperty(name="kafka.bootstrap.servers")
    String host;


    @Inject @Channel("usuarios-create") Emitter<Usuario> createUsers;
    @Inject @Channel("usuarios-delete") Emitter<Usuario> deleteUsers;
    @Inject @Channel("usuarios-update") Emitter<Usuario> updateUsers;
    @Inject @Channel("usuarios-sendemail") Emitter<Usuario> sendEmailUsers;
    @Inject @Channel("usuarios-sendsms") Emitter<Usuario> sendSMSUsers;

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

    }

    @Incoming("handlednotification-usuarios")
    public Usuario incominghandledNotification(Usuario usuario){
        LOGGER.info("USUARIO: " + usuario.toString());
        String canalPrefer = usuario.getCanalPrefer();
        String canalContac = usuario.getCanalContac();
        String estadoNotif = usuario.getEstadoNofif();
         if(estadoNotif != null){
            LOGGER.info("Procedemos a actualizar el usuario");
            updateUsers.send(usuario);
        }else if(!canalPrefer.equals(canalContac)){
             LOGGER.info("Se tendrá que enviar al topic de: "+canalContac);
             incomingNotification(usuario);
         }
        return usuario;
    }

    @Incoming("usuarios-sendnotification")
    public Usuario incomingNotification(Usuario usuario){
        LOGGER.info("USUARIO: " + usuario.toString());
        String canalPrefer = usuario.getCanalPrefer();
        String canalContac = usuario.getCanalContac();
        String canal = canalPrefer.toLowerCase();
        LOGGER.info("Se tendrá que enviar al topic de: "+canalContac);
        if(!canalPrefer.equals(canalContac)){
            canal = canalContac.toLowerCase();
        }
        sendNotification(canal, usuario);
        return usuario;
    }
    private void sendNotification(String canal, Usuario usuario){
        LOGGER.info("Enviamos: {"+usuario+"}, por el canal: {"+canal+"}" );
        switch (canal){
            case "email":
                LOGGER.info("usuarios-sendEmail");
                sendEmailUsers.send(usuario);
                break;
            case "telefono":
                LOGGER.info("usuarios-sendSMS");
                sendSMSUsers.send(usuario);


        }
    }


}
