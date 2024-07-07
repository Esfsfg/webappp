import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.bson.Document;
import java.io.IOException;
import java.io.PrintWriter;

public class Insert extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String country = req.getParameter("country");

        PrintWriter pw = res.getWriter();

        try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Document> collection = database.getCollection("pcm");

            Document doc = new Document("id", Integer.parseInt(id))
                    .append("name", name)
                    .append("country", country);

            collection.insertOne(doc);

            res.sendRedirect("Home.html");
        } catch (Exception e) {
            pw.print(e);
        }
    }
}
