package mongoPojo;

import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;

public class Federation {
	
	private ObjectId id;
	
	private String nom;
	
	private String acronyme;
	
	private Adresse adresse;
	
	private List<String> disciplines;
	
	private List<Integer> sportifs;

	public String getNom() {
		return nom;
	}

	public ObjectId geId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAcronyme() {
		return acronyme;
	}

	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<String> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<String> disciplines) {
		this.disciplines = disciplines;
	}

	public List<Integer> getSportifs() {
		return sportifs;
	}

	public void setSportifs(List<Integer> sportifs) {
		this.sportifs = sportifs;
	}

	public Federation(ObjectId id, String nom, String acronyme, Adresse adresse, List<String> disciplines,
			List<Integer> sportifs) {
		super();
		this.id = id;
		this.nom = nom;
		this.acronyme = acronyme;
		this.adresse = adresse;
		this.disciplines = disciplines;
		this.sportifs = sportifs;
	}
	
	public Federation() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(acronyme, adresse, disciplines, id, nom, sportifs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Federation other = (Federation) obj;
		return Objects.equals(acronyme, other.acronyme) && Objects.equals(adresse, other.adresse)
				&& Objects.equals(disciplines, other.disciplines) && Objects.equals(id, other.id)
				&& Objects.equals(nom, other.nom) && Objects.equals(sportifs, other.sportifs);
	}
}
