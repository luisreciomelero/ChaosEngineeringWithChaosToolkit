package es.upm.dit.muirst.tfm.services;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class Service {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    @Inject
    ReactiveMailer reactiveMailer;
    private Vertx vertx = Vertx.vertx();

    @Inject @Channel("sendEmail-usuarios")
    Emitter<Usuario> sendEmailUsers;

    public void sendMail(Usuario usuario){

        String email = usuario.getEmail();
        String nombre = usuario.getNombre();
        String telefono = usuario.getTelefono();
        LOGGER.info("vamos a enviar un email a: " + email);
        Uni<Void> stage = reactiveMailer.send(Mail.withText(email, "Created", "Created: "+nombre));
        stage.subscribe().with(
                item -> {
                    usuario.setEstadoNofif("Enviado por email");
                    System.out.println("Email enviado: "+ usuario);
                    sendEmailUsers.send(usuario);

                },
                failure -> {
                    System.out.println("No ha sido posible enviar el email ");
                    if(telefono!=null && telefono.equals("")){
                        usuario.setCanalContac("Telefono");
                        sendEmailUsers.send(usuario);
                    }
                });



    }
}
