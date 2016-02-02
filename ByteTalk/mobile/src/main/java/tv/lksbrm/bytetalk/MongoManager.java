package tv.lksbrm.bytetalk;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Created by Lukas on 02.02.2016.
 */
public class MongoManager
{

    DB db;
    DBCollection collection;
    DBObject user;

    public void init()
    {
        db = MainActivity.mc.getDB("users");
        collection = db.getCollection("users");
    }

    public void createUser(String email, String name, String password)
    {
        user = new BasicDBObject("name", name)
                         .append("password", password)
                         .append("email", email);

        collection.insert(user);
    }
}
