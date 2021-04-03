package org.acme.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsuarioPostgres {

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


}
