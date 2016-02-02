package tv.lksbrm.bytetalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity
{

    public static MongoClient mc;
    TextView tv;
    MongoManager mm = new MongoManager();




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init()
    {
        tv = (TextView) findViewById(R.id.textView);
        try
        {

            mc = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            mm.init();
            tv.setText("Erfolgreich zur Datenbank verbunden!");


        }catch(UnknownHostException e)
        {
            tv.setText("Fehler beim Verbinden zur Datenbank!");
        }
    }
}
