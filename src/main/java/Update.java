import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Update extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String country = req.getParameter("country");

        PrintWriter t = res.getWriter();

        try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Document> collection = database.getCollection("pcm");

            Document query = new Document("id", Integer.parseInt(id));
            Document update = new Document("$set", new Document("name", name).append("country", country));

            collection.updateOne(query, update);

            res.sendRedirect("Home.html");
        } catch (Exception e) {
            t.print(e);
        }
        t.close();
    }
}
