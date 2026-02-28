

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.Federation;
import mongoPojo.Sportif;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class MongoDBServlet
 */
public class MongoDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ArrayList<Federation> getFederations() {
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		
		ConnectionString connectionString = new ConnectionString("mongodb://obiwan.univ-brest.fr:27017");
		MongoClient mongoClient = MongoClients.create(connectionString);
		MongoDatabase database = mongoClient.getDatabase("e22510996").withCodecRegistry(pojoCodecRegistry);
		System.out.println("Connexion �tablie\n");
		
		MongoCollection<Federation> federations = database.getCollection("federations", Federation.class);
		MongoCollection<Sportif> sportifs = database.getCollection("sportifs", Sportif.class);
		
		ArrayList<Federation> res = new ArrayList<Federation>();

		for(Federation fed : federations.find()) {
			res.add(fed);
		}
		return res;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			String operation = request.getParameter("operation");
			if (operation.equals("listeFederations")) {
				ArrayList<Federation> federations = getFederations();
				out.println("<title>Federations</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3>Liste des fédérations :</h3>");
				for (Federation fed : federations) {
					out.println("<ul>");
					out.println("<li>"+ fed.getNom() +"</li>");
					out.println("</ul>");
				}
				
			}
		} catch (Exception e) {
			out.println("<p><b>Erreur </b></p>");
			e.printStackTrace(out);
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}