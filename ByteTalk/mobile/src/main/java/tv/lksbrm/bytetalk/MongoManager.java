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

    public void createUser(final String email, final String name, final String password)
    {


        Thread network = new Thread(new Runnable()
       {

           @Override
           public void run()
           {


               collection.insert((DBObject) new BasicDBObject("name", name)
                       .append("password", password)
                       .append("email", email));

           }
       });
       network.start();

    }
    DBObject RES = new BasicDBObject("email", "").append("name", "");
    public DBObject getUser(final String name)
    {
        Thread nete = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(collection.find().size());
                for(DBObject all : collection.find())
                {
                    if(all.get("name") == name)
                    {
                        RES = all;
                    }
                }

            }
        });

        return RES;


    }

    DBObject RES2 = new BasicDBObject("email", "").append("name", "");
    public DBObject getUserByEmail(final String email)
    {
      Thread nete = new Thread(new Runnable()
      {
          @Override
          public void run()
          {
              DBObject query = new BasicDBObject("email", email);
              DBCursor cursor = collection.find(query);
              RES2 = cursor.one();

          }
      });

          return RES2;


    }
    }
