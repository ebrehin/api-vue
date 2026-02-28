import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mongoPojo.*;

public class TestMongoPOJO {

	public static void main(String[] args) {

		// connexion � la base Mongo et � la base "perso"
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		
		ConnectionString connectionString = new ConnectionString("mongodb://obiwan.univ-brest.fr:27017");
		MongoClient mongoClient = MongoClients.create(connectionString);
		MongoDatabase database = mongoClient.getDatabase("e22510996").withCodecRegistry(pojoCodecRegistry);
		System.out.println("Connexion �tablie\n");
		
		MongoCollection<Federation> federations = database.getCollection("federations", Federation.class);
		MongoCollection<Sportif> sportifs = database.getCollection("sportifs", Sportif.class);
		
		for(Federation fed : federations.find()) {
			System.out.println("\n** "+fed.getNom()+" ("+fed.getAcronyme()+") **");
			System.out.println("Adresse : "+fed.getAdresse());
			System.out.print("Disciplines : ");
			for(String disc : fed.getDisciplines()) 
				System.out.print(disc+ " ");
			System.out.println("\nSportifs : ");
			for(Integer idSportif : fed.getSportifs()) {
				// on fait la jointure � la main avec un find
				Sportif sportif = (Sportif) sportifs.find(eq("_id",idSportif)).first();
				System.out.println(" - "+sportif.getPrenom()+ " "+sportif.getNom());
			}
		}
	       
		// ajout d'un sportif
		/*
		Sportif saturnin = new Sportif();
		saturnin.setId(12);
		saturnin.setNom("Legrand");
		saturnin.setPrenom("Saturnin");
		saturnin.setAge(21);
		saturnin.setAdresse(new Adresse("12 rue de Navarre", "Pau", 64000));
		ArrayList<String> disciplines = new ArrayList<>();
		disciplines.add("marathon");
		disciplines.add("descente");
		saturnin.setDisciplines(disciplines);
		sportifs.insertOne(saturnin);
		*/
	}
}
