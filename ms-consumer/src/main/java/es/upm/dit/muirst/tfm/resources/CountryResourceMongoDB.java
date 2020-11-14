package es.upm.dit.muirst.tfm.resources;

import es.upm.dit.muirst.tfm.entities.Country;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import org.bson.Document;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CountryResourceMongoDB implements PanacheMongoRepository<Country>{
   /* @Inject
    ReactiveMongoClient mongoClient;

    public Uni<Void> add(Country country) {
        Document document = new Document()
                .append("name", country.getName())
                .append("capital", country.getCapital())
                .append("alpha2code",country.getAlpha2Code());
        return getCollection().insertOne(document).onItem().ignore().andContinueWithNull();
    }
    private ReactiveMongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("country").getCollection("country");
    }

    public Uni<List<Country>> list() {
         return getCollection().find()
                .map(doc -> {
                    Country country = new Country();
                    country.setName(doc.getString("name"));
                    country.setCapital(doc.getString("capital"));
                    country.setAlpha2Code(doc.getString("alpha2Code"));
                    return country;
                }).collectItems().asList();
    }
    */
   public Country findByName(String name){
       return find("name", name).firstResult();
   }

    public void deleteByName(String name){
        delete("name", name);
    }

}
