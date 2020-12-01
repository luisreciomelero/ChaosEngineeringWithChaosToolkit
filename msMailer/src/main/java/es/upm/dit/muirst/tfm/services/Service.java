package es.upm.dit.muirst.tfm.services;

import es.upm.dit.muirst.tfm.entities.Usuario;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class Service {

    private Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

    @Inject
    ReactiveMailer reactiveMailer;
    private Vertx vertx = Vertx.vertx();

    public void sendMail(Usuario usuario){

        String email = usuario.getEmail();
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        String dni = usuario.getDNI();
        LOGGER.info("vamos a enviar un email a: " + email);

        Uni<Void> stage = reactiveMailer.send(Mail.withText(email, "Created", "Created: "+nombre));
        stage.subscribe().with(
                item -> System.out.println("Got item: " + item),
                failure -> System.out.println("Got a failure " + failure.getMessage()));
        /*MailConfig config = new MailConfig();
        config.setHostname("smtp.sendgrid.net");
        config.setPort(465);
        config.setSsl(true);
        config.setUsername("apikey");
        config.setPassword("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");
        MailMessage message = new MailMessage();
        message.setFrom("tfm.muirst.lrm@gmail.com");
        message.setTo(email);
        message.setText("Usuario creado");
        //message.setHtml("this is html text <a href=\"http://vertx.io\">vertx.io</a>");


        MailClient mailClient = MailClient.create(vertx, config);

        mailClient.sendMail(message, result -> {
            if (result.succeeded()) {
                LOGGER.info("OKKKK");
                System.out.println(result.result());
            } else {
                LOGGER.info("NO OKKKK");
                result.cause().printStackTrace();
            }
        });*/

    }
}
