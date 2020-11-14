package es.upm.dit.muirst.tfm.resources;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class CountryResource {

 /*   @Inject
    EntityManager entityManager;

    *//*public List<Country> getCountry() {
        return entityManager.createQuery("SELECT c FROM Countries c").getResultList();
    }

    public Country getCountry(Long id) {
        return entityManager.find(Country.class, id);
    }*//*

    @Transactional(Transactional.TxType.REQUIRED)
    public Country addCountry(Country country) {
        entityManager.persist(country);
        return country;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateCountry(Long id, Country country) {
        Country countryToUpdate = entityManager.find(Country.class, id);
        if (null != countryToUpdate) {
            countryToUpdate.setAlpha2Code(country.getAlpha2Code());
            countryToUpdate.setCapital(country.getCapital());
            countryToUpdate.setName(country.getName());
        } else {
            throw new RuntimeException("No such country available");
        }
    }*/
/*
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteContact(Long id) {
        Country country = getCountry(id);
        entityManager.remove(country);
    }*/
}
