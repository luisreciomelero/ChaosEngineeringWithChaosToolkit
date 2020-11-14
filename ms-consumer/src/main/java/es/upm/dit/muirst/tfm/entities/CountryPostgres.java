package es.upm.dit.muirst.tfm.entities;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class CountryPostgres extends PanacheEntity {

    public String name;
    public String alpha2Code;
    public String capital;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryPostgres that = (CountryPostgres) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(alpha2Code, that.alpha2Code) &&
                Objects.equals(capital, that.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, alpha2Code, capital);
    }

    @Override
    public String toString() {
        return "CountryPostgres{" +
                " name='" + name + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }

    public static CountryPostgres findByName(String name){
        return find("name", name).firstResult();
    }

    public static void add(CountryPostgres countryPostgres){
        countryPostgres.persist();
    }

}
