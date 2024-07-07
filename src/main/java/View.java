import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class View extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pr = res.getWriter();

        try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Document> collection = database.getCollection("pcm");

            MongoCursor<Document> cursor = collection.find().iterator();

            res.setContentType("text/html");
            pr.print("<html><body>");

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                pr.print("<form>");
                pr.println("ID:<input type='number' name='id' value='" + doc.getInteger("id") + "'/><br>");
                pr.println("NAME:<input type='text' name='name' value='" + doc.getString("name") + "'/><br>");
                pr.println("COUNTRY:<input type='text' name='country' value='" + doc.getString("country") + "'/><br>");
                pr.print("</form>");
            }
            pr.print("</body></html>");
        } catch (Exception e) {
            pr.print(e);
        }
    }
}
