package donnees;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * POJO Discipline
 * @author Eric
 */
@Entity
@Table(name = "discipline")
@NamedQueries({
    @NamedQuery(name = "Discipline.findAll", query = "SELECT d FROM Discipline d"),
    @NamedQuery(name = "Discipline.findByCodeDiscipline", query = "SELECT d FROM Discipline d WHERE d.codeDiscipline = :codeDiscipline"),
    @NamedQuery(name = "Discipline.findByIntitule", query = "SELECT d FROM Discipline d WHERE d.intitule = :intitule")})
public class Discipline implements java.io.Serializable {
    
	private static final long serialVersionUID = -5446896659949901895L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_discipline")
	private int codeDiscipline;
    
	@Column(name = "intitule")
    private String intitule;
    
    @JoinColumn(name = "code_sport", referencedColumnName = "code_sport")
    @ManyToOne
    private Sport sport;
    
    @ManyToMany(mappedBy = "disciplines")
    private Set<Sportif> sportifs;

	public int getCodeDiscipline() {
        return codeDiscipline;
    }

    public void setCodeDiscipline(int codeDiscipline) {
        this.codeDiscipline = codeDiscipline;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    
    public Set<Sportif> getSportifs() {
		return sportifs;
	}

	public void setSportifs(Set<Sportif> sportifs) {
		this.sportifs = sportifs;
	}

    public Discipline(int codeDiscipline, String intitule, Sport sport) {
        this.codeDiscipline = codeDiscipline;
        this.intitule = intitule;
        this.sport = sport;
    }
    
    public Discipline(int codeDiscipline, String intitule, Sportif sportif) {
        this.codeDiscipline = codeDiscipline;
        this.intitule = intitule;
        this.sportifs.add(sportif);
    }
    
    public Discipline() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codeDiscipline;
        hash = 97 * hash + Objects.hashCode(this.intitule);
        hash = 97 * hash + Objects.hashCode(this.sport);
        hash = 97 * hash + Objects.hashCode(this.sportifs);
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
        final Discipline other = (Discipline) obj;
        if (this.codeDiscipline != other.codeDiscipline) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        if (!Objects.equals(this.sport, other.sport)) {
            return false;
        }
        if (!Objects.equals(this.sportifs, other.sportifs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Discipline{" + "codeDiscipline=" + codeDiscipline + ", intitule=" + intitule + ", sport=" + sport + '}';
    }
    
}
