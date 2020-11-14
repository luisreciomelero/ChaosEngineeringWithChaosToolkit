package es.upm.dit.muirst.tfm.adapters;

import es.upm.dit.muirst.tfm.entities.CountryPostgres;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PostgresAdapter {

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CountryPostgres> findAll(){
        return CountryPostgres.listAll();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public CountryPostgres findCountryByName (String name){
        return CountryPostgres.find("name", name).firstResult();
    }

    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    public CountryPostgres persistCountry( CountryPostgres countryPostgres){
        CountryPostgres.persist(countryPostgres);
        return countryPostgres;
    }

}
