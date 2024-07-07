import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Search extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        PrintWriter t = res.getWriter();

        try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Document> collection = database.getCollection("pcm");

            Document query = new Document("id", Integer.parseInt(id));
            Document result = collection.find(query).first();

            res.setContentType("text/html");
            if (result != null) {
                t.println("ID:<input type='number' name='id' value='" + result.getInteger("id") + "'/><br>");
                t.println("NAME:<input type='text' name='name' value='" + result.getString("name") + "'/><br>");
                t.println("COUNTRY:<input type='text' name='country' value='" + result.getString("country") + "'/><br>");
            } else {
                t.println("No record found");
            }
        } catch (Exception e) {
            t.print(e);
        }
        t.close();
    }
}
