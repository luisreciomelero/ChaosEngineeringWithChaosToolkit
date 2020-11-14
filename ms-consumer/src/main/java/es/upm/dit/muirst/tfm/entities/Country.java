package es.upm.dit.muirst.tfm.entities;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import javax.persistence.*;
import java.io.Serializable;


public class Country   {

    private Long id;
    private String name;
    private String alpha2Code;
    private String capital;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//public List<Currency> currencies;

    public Country(){

    }

    public Country(String name, String alpha2Code, String capital){
        this.name=name;
        this.alpha2Code=alpha2Code;
        this.capital=capital;
    }

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
    /*
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }*/

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}