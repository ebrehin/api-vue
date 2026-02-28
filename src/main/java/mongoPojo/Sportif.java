package mongoPojo;

import java.util.List;
import java.util.Objects;

public class Sportif {
	
	private int id;
	
	private String prenom;
	
	private String nom;
	
	private int age;
	
	private String genre;
	
	private Adresse adresse;
	
	private List<String> disciplines;
	
	private boolean marie;

	public boolean getMarie() {
		return marie;
	}

	public void setMarie(boolean marie) {
		this.marie = marie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public Sportif(int id, String prenom, String nom, int age, String genre, Adresse adresse,
			List<String> disciplines, boolean marie) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.genre = genre;
		this.adresse = adresse;
		this.disciplines = disciplines;
		this.marie = marie;
	}
	
	public Sportif() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, age, disciplines, genre, id, nom, prenom, marie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sportif other = (Sportif) obj;
		return Objects.equals(adresse, other.adresse) && age == other.age
				&& Objects.equals(disciplines, other.disciplines) && Objects.equals(genre, other.genre)
				&& id == other.id && Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& marie == other.marie;
	}
}
