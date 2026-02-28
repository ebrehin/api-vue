package donnees;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sportif")
@NamedQueries({
    @NamedQuery(name = "Sportif.findAll", query = "SELECT s FROM Sportif s"),
    @NamedQuery(name = "Sportif.findByCodeSportif", query = "SELECT s FROM Sportif s WHERE s.codeSportif = :codeSportif"),
    @NamedQuery(name = "Sportif.findByNom", query = "SELECT s FROM Sportif s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sportif.findByRue", query = "SELECT s FROM Sportif s WHERE s.rue = :rue"),
    @NamedQuery(name = "Sportif.findByVille", query = "SELECT s FROM Sportif s WHERE s.ville = :ville"),
    @NamedQuery(name = "Sportif.findByCodePostal", query = "SELECT s FROM Sportif s WHERE s.code_postal = :codePostal")})
public class Sportif implements java.io.Serializable {
    
	private static final long serialVersionUID = -1479329647238860078L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name = "code_sportif")
	private int codeSportif;
    
	@Column(name = "nom")
    private String nom;
    
	@Column(name = "rue")
    private String rue;
    
	@Column(name = "ville")
    private String ville;
    
	@Column(name = "code_postal")
    private String code_postal;
    
	@JoinTable(name = "pratique", joinColumns = {
        @JoinColumn(name = "code_sportif", referencedColumnName = "code_sportif")}, inverseJoinColumns = {
        @JoinColumn(name = "code_discipline", referencedColumnName = "code_discipline")})
    @ManyToMany
    private Set<Discipline> disciplines;

	public void setCodeSportif(int codeSportif) {
		this.codeSportif = codeSportif;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	
	public int getCodeSportif() {
		return codeSportif;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getRue() {
		return rue;
	}
	
	public String getVille() {
		return ville;
	}
	
	public String getCode_postal() {
		return code_postal;
	}

	public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public void addDiscipline(Discipline disc) {
        this.disciplines.add(disc);
    }
    
    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
    
    public void removeDiscipline(Discipline disc) {
        this.disciplines.remove(disc);
    }
    
    public Sportif(int codeSportif, String nom, String rue, String ville, String code_postal,
			Set<Discipline> disciplines) {
		this.codeSportif = codeSportif;
		this.nom = nom;
		this.rue = rue;
		this.ville = ville;
		this.code_postal = code_postal;
		this.disciplines = disciplines;
	}

	public Sportif() {
		this.disciplines = new HashSet<Discipline>();
	}

	@Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.codeSportif;
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.rue);
        hash = 67 * hash + Objects.hashCode(this.ville);
        hash = 67 * hash + Objects.hashCode(this.code_postal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sportif other = (Sportif) obj;
        if (this.codeSportif != other.codeSportif) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.rue, other.rue)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.code_postal, other.code_postal)) {
            return false;
        }
        if (!Objects.equals(this.disciplines, other.disciplines)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sportif{" + "codeSportif=" + codeSportif + ", nom=" + nom + '}';
    }
    
    
}
