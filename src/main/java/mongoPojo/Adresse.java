package mongoPojo;

import java.util.Objects;

public class Adresse {

		private String rue;
		
		private String ville;
		
		private int codePostal;

		public String getRue() {
			return rue;
		}

		public void setRue(String rue) {
			this.rue = rue;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public int getCodePostal() {
			return codePostal;
		}

		public void setCodePostal(int codePostal) {
			this.codePostal = codePostal;
		}

		public Adresse(String rue, String ville, int codePostal) {
			super();
			this.rue = rue;
			this.ville = ville;
			this.codePostal = codePostal;
		}
		
		public Adresse() {
			super();
		}

		@Override
		public int hashCode() {
			return Objects.hash(codePostal, rue, ville);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Adresse other = (Adresse) obj;
			return codePostal == other.codePostal && Objects.equals(rue, other.rue)
					&& Objects.equals(ville, other.ville);
		}
		
		@Override
		public String toString() {
			return this.rue + " - "+this.codePostal + " "+this.ville;
		}
		
}
