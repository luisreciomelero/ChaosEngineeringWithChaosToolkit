package es.upm.dit.muirst.tfm.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class UsuarioPostgres extends PanacheEntityBase {
    @Id
    private String DNI;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;
    private String canalPrefer;
    private String canalContac;
    private String estadoNofif;

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCanalPrefer() {
        return canalPrefer;
    }

    public void setCanalPrefer(String canalPrefer) {
        this.canalPrefer = canalPrefer;
    }

    public String getCanalContac() {
        return canalContac;
    }

    public void setCanalContac(String canalContac) {
        this.canalContac = canalContac;
    }

    public String getEstadoNofif() {
        return estadoNofif;
    }

    public void setEstadoNofif(String estadoNofif) {
        this.estadoNofif = estadoNofif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPostgres usuarioPostgres = (UsuarioPostgres) o;
        return Objects.equals(DNI, usuarioPostgres.DNI) &&
                Objects.equals(nombre, usuarioPostgres.nombre) &&
                Objects.equals(apellido, usuarioPostgres.apellido) &&
                Objects.equals(telefono, usuarioPostgres.telefono) &&
                Objects.equals(email, usuarioPostgres.email) &&
                Objects.equals(password, usuarioPostgres.password) &&
                Objects.equals(canalPrefer, usuarioPostgres.canalPrefer) &&
                Objects.equals(canalContac, usuarioPostgres.canalContac) &&
                Objects.equals(estadoNofif, usuarioPostgres.estadoNofif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI, nombre, apellido, telefono, email, password, canalPrefer, canalContac, estadoNofif);
    }

    @Override
    public String toString() {
        return "UsuarioPostgres{" +
                "DNI='" + DNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", canalPrefer='" + canalPrefer + '\'' +
                ", canalContac='" + canalContac + '\'' +
                ", estadoNofif='" + estadoNofif + '\'' +
                '}';
    }
    /*public static void add(UsuarioPostgres usuarioPostgres){
        usuarioPostgres.persist();
    }
    */
}
