package tv.lksbrm.bytetalk;

import android.content.Intent;
import android.os.Looper;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.Map;

/**
 * Created by Lukas on 06.02.2016.
 */
public class LoginManager
{
    public DBObject RES = new BasicDBObject("name", "null").append("password", "null");
    public DBCursor cursor = MainActivity.getMongoManager().collection.find(RES);
    public boolean B = false;
    public static String password;

    public MainActivity ma;
    public LoginManager(MainActivity m)
    {
        ma = m;
    }

    public void LogUserIn(final String name, final String password)
    {
        if(userExists(name))
        {
                  if(passwordFits(name, password))
                  {
                      MainActivity.prefseditor.putBoolean("loggedin", true);
                      MainActivity.prefseditor.commit();
                      MainActivity.prefseditor.putString("name", name);
                      MainActivity.prefseditor.commit();
                      MainActivity.prefseditor.putString("password", password);
                      MainActivity.prefseditor.commit();
                      System.out.println("USER STIMMT");

                      B = true;
                  } else
                  {
                      B = false;
                  }
               }else
               {
                  B = false;
               }

            }



    boolean boo = false;
    public boolean userExists(final String name)
    {
        System.err.println("EX Methode gestartet!");
        Thread exist = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.err.println("EX Thread gestartet!");
                for(DBObject all : MainActivity.mm.collection.find())
                {
                    System.err.println("EX For- gestartet!");
                    if(all.get("name") == name)
                    {
                        System.err.println("EX User gefunden!");
                        boo = true;
                    }
                }
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        });
        exist.start();
        return boo;


    }
    boolean boo2 = false;
    public boolean passwordFits(final String name, final String password)
    {

       Thread thread = new Thread(new Runnable()
       {
           @Override
           public void run()
           {
               for(DBObject all : MainActivity.getMongoManager().collection.find())
               {
                   if(all.get("name") == name)
                   {
                       if(all.get("password") == password)
                       {
                           boo2 = true;
                       }
                   }
               }
               try
               {
                   Thread.sleep(200);
               } catch (InterruptedException e)
               {
                   e.printStackTrace();
               }
           }
       });
        thread.start();
        return boo2;

    }


}
