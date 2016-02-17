package tv.lksbrm.bytetalk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    public static MongoClient mc;
    TextView tv;
    TextView tv2;
    Button reg;
    Button login;
    static MongoManager mm = new MongoManager();

    EditText username;
    EditText password;

    Toolbar toolbar;


    public static SharedPreferences prefs;
    public static SharedPreferences.Editor prefseditor;

    public LoginManager lm;


    public static MongoManager getMongoManager()
    {
        return mm;
    }


    public void goToUserScreen()
    {

            Intent in = new Intent(MainActivity.this, UserActivity.class);
            startActivity(in);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        lm = new LoginManager(this);

        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        login = (Button) findViewById(R.id.button);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitle("ByteTalk");
        toolbar.setSubtitle("Bitte logge dich ein!");

        prefs = this.getSharedPreferences("prefslogin", MODE_PRIVATE);
        prefseditor = prefs.edit();

        login.setOnClickListener(this);
        reg.setOnClickListener(this);

        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String s = "Anzahl: " + mm.collection.find().size();
                tv.setText(s);
            }
        });
        t.start();





    }

       @Override
       public void onClick(View v)
       {

           switch(v.getId())
           {
               case R.id.button:
               {
                   if(lm.userExists("test"))
                   {
                       Toast.makeText(getApplicationContext(), "User exists", Toast.LENGTH_SHORT);
                   }
                   if(lm.B)
                   {
                       goToUserScreen();
                   }else
                   {
                       Toast.makeText(getApplicationContext(), "Login invalid!", Toast.LENGTH_SHORT);
                   }
                   break;
               }
               case R.id.button2:
               {

                   Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                   startActivity(i);
                   break;
               }


           }


       }

    public void init()
    {
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        reg = (Button) findViewById(R.id.button2);
        try
        {

            mc = new MongoClient(new MongoClientURI("mongodb://192.168.178.41:27017"));
            mm.init();
            tv.setText("Erfolgreich zur Datenbank verbunden!");


        }catch(UnknownHostException e)
        {
            tv.setText("Fehler beim Verbinden zur Datenbank!");
        }
    }

    public void setText(String text)
    {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(text);
    }
}
