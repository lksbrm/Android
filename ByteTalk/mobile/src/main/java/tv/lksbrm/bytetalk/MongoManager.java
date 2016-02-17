package tv.lksbrm.bytetalk;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Created by Lukas on 02.02.2016.
 */
public class MongoManager
{

    DB db;
    DBCollection collection;

    public void init()
    {
        db = MainActivity.mc.getDB("user");
        collection = db.getCollection("users");

    }

    public void createUser(String email, String name, String password)
    {
        DBObject user = new BasicDBObject("name", name)
                         .append("password", password)
                         .append("email", email);

        collection.insert(user);
        i = q/t;
    }

    public DBObject getUser(String name) {
        DBObject query = new BasicDBObject("name", name);
        DBCursor cursor = collection.find(query);
        DBObject result = cursor.one();

        return result;
    }

    public DBObject getUserByEmail(String email) {
        DBObject query = new BasicDBObject("email", email);
        DBCursor cursor = collection.find(query);
        DBObject result = cursor.one();

        return result;
    }
    }
