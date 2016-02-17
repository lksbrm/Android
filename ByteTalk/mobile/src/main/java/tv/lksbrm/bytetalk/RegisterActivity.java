package tv.lksbrm.bytetalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mongodb.DBObject;

public class RegisterActivity extends AppCompatActivity
{


    TextView RESULT;

    EditText email;
    EditText name;
    EditText password;

    Button submit;


    MongoManager mm = MainActivity.getMongoManager();

    String maymail = "";
    String mayname = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RESULT = (TextView) findViewById(R.id.textView7);

        email = (EditText) findViewById(R.id.editText3);
        name = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText5);

        submit = (Button) findViewById(R.id.button3);


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final String Email = email.getText().toString();
                final String Name = name.getText().toString();
                final String Password = password.getText().toString();

               try {
                   maymail = (String) mm.getUserByEmail(Email).get("email");
                   mayname = (String) mm.getUser(Name).get("name");
               }catch(NullPointerException e)
               {
                   maymail = "";
                   mayname = "";
               }

                if(maymail == "" && mayname == "")
                {
                    mm.createUser(Email, Name, Password);
                    setContentView(R.layout.register_result);
                    setContentView(R.layout.register_result);
                    RESULT.setText("Erfolgreich Registriert!");

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }else
                {
                    RESULT.setText("E-Mail oder Name bereits registriert!");
                }

            }
        });

    }

}
