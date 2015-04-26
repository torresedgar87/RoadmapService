package roadmap.dreamers.app.dreamersroadmap;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Edgar on 4/24/15.
 */
public class UserInfoActivity extends Activity
{
    private EditText nameEditText = null;
    private EditText emailEditText = null;
    private EditText passwordEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameEditText = (EditText) findViewById(R.id.editText3);
        emailEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);
    }

    @Override
    protected void onStart() {
        super.onStart();

        AsyncClass c = new AsyncClass();
        c.execute("http://192.168.0.106:8080/RESTfulExample/rest/user/" + "torresedgar87@yahoo.com");

        JSONObject user = null;
        try {
            user = new JSONObject(c.get());
            nameEditText.setText(user.get("name").toString());
            emailEditText.setText(user.get("email").toString());
            passwordEditText.setText(user.get("password").toString());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    private class AsyncClass extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String restCall = strings[0];
            String finalLine= "";

            // HTTP Get
            try {

                URL url = new URL(restCall);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    finalLine += inputLine;

                in.close();

            } catch (Exception e ) {

                System.out.println(e.getMessage());
                return e.getMessage();
            }

            return finalLine;
        }
    }
}
