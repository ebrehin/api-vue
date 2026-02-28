import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import donnees.*;

public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public List<Sportif> getListeSportifs() {
		// requ�te JPQL pour r�cup�rer les sportifs dans la BDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery("SELECT s FROM Sportif s");
		return (List<Sportif>) requete.getResultList();
	}
	
	public void ajouteSport(String intitule) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
		EntityManager em = emf.createEntityManager();
		Query requete = em.createQuery("INSERT INTO 'sport' ('intitule') VALUES ('" + intitule +"')");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("listeSportif")) {
			// r�cup�re la liste des sportifs et l'associe � la requ�te HTTP
			request.setAttribute("sportifs", this.getListeSportifs());
			// forwarde la requ�te � la page JSP
			getServletConfig().getServletContext().getRequestDispatcher("/afficheSportifs.jsp")
				.forward(request, response);
		} else if (operation.equals("ajouteSport")) {
			request.setAttribute("methode", "GET");
			// forwarde la requ�te � la page JSP
			getServletConfig().getServletContext().getRequestDispatcher("/ajouteSport.jsp")
				.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String intitule = request.getParameter("intitule");
		if (intitule != "") {
			this.ajouteSport(intitule);			
		}
	}

}