import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Delete extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        PrintWriter t = res.getWriter();

        try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Document> collection = database.getCollection("pcm");

            Document query = new Document("id", Integer.parseInt(id));

            collection.deleteOne(query);

            res.sendRedirect("Home.html");
        } catch (Exception e) {
            t.print(e);
        }
    }
}
