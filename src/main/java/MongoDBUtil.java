import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        }
        return mongoClient.getDatabase("yourDatabaseName");
    }
}
