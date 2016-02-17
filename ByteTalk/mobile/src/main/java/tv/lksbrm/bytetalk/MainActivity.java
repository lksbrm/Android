package tv.lksbrm.bytetalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity
{

    public static MongoClient mc;
    TextView tv;
    TextView tv2;
    Button reg;
    static MongoManager mm = new MongoManager();




    public static MongoManager getMongoManager()
    {
        return mm;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


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
}
