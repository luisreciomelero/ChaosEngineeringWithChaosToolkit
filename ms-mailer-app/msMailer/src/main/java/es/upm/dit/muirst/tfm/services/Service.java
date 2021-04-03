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
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.logging.Logger;

/*
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/

@ApplicationScoped
public class Service {

    /*private final Properties properties = new Properties();
    private Session session;
*/
    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    @ConfigProperty(name = "quarkus.mailer.from")
    String from;

    @ConfigProperty(name = "quarkus.mailer.port")
    String port;

     @ConfigProperty(name = "quarkus.mailer.host")
    String host;

    @ConfigProperty(name = "quarkus.mailer.username")
    String username;

    @ConfigProperty(name = "quarkus.mailer.password")
    String password;

    @ConfigProperty(name = "sendsmtp.enable")
    boolean enable_smtp;

    @Inject
    ReactiveMailer reactiveMailer;


    @Inject @Channel("sendEmail-usuarios")
    Emitter<Usuario> sendEmailUsers;

    public void sendMail(Usuario usuario){

        String email = usuario.getEmail();
        String nombre = usuario.getNombre();
        String telefono = usuario.getTelefono();
        LOGGER.info("vamos a enviar un email a: " + email);
        LOGGER.info("From: {"+from+"}, Host: {"+host+"}, port: {"+port+"}, username:{"+username+"}, password: {"+password+"}");
        if(enable_smtp) {
            Uni<Void> stage = reactiveMailer.send(Mail.withText(email, "Created", "Created: " + nombre));
            stage.subscribe().with(
                    item -> {
                        usuario.setEstadoNofif("Enviado por email");
                        System.out.println("Email enviado: " + usuario);
                        sendEmailUsers.send(usuario);

                    },
                    failure -> {
                        System.out.println("No ha sido posible enviar el email ");
                        if (telefono != null && !telefono.equals("")) {
                            usuario.setCanalContac("Telefono");
                            sendEmailUsers.send(usuario);
                        }
                    });
        }else{
            usuario.setEstadoNofif("Enviado por email");
            System.out.println("Email enviado: " + usuario);
            sendEmailUsers.send(usuario);
        }


    }



}
