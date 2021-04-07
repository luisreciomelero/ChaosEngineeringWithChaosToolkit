package es.upm.dit.muirst.tfm.services;

import es.upm.dit.muirst.tfm.entities.Usuario;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class Service {


    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    @ConfigProperty(name = "sendsms.enable")
    boolean enable_sms;


    @Inject @Channel("handlednotification-usuarios")
    Emitter<Usuario> sendSMSUsers;

    public void sendSMS(Usuario usuario){

        String email = usuario.getEmail();
        String nombre = usuario.getNombre();
        String telefono = usuario.getTelefono();
        LOGGER.info("vamos a enviar un sms a: " + telefono);
        if(enable_sms) {
            usuario.setEstadoNofif("Enviado sms");
            System.out.println("SMS enviado: " + usuario);
            sendSMSUsers.send(usuario);

        }else{
            System.out.println("No ha sido posible enviar el sms ");
            if(!usuario.getCanalContac().equals(usuario.getCanalPrefer())){
                usuario.setEstadoNofif("No se ha podido contactar con el usuario");
                System.out.println("SMS enviado: " + usuario);
                sendSMSUsers.send(usuario);
            }else if (email != null && !email.equals("")) {
                usuario.setCanalContac("Email");
                sendSMSUsers.send(usuario);
            }
        }


    }



}
