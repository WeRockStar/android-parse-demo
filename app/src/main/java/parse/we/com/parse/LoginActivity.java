package parse.we.com.parse;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {


    private EditText usernameLogin;
    private EditText passwordLogin;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this, "95I4LvWem7H4dIYI1MfnWRQjRdcziQHh76Rh6x9A", "yKUWKoKqe1hnGtsX6io12mZOSjkGdN5r7RliyR6Z");
        registerButton = (Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        usernameLogin = (EditText) findViewById(R.id.usernameLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString().trim();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        try {
                            if (e == null) {
                                //login success
                                Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                startActivity(intent);
                            } else {
                                //login fail
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle("Warning!!!");
                                builder.setMessage("Sorry, username or password invalid");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        passwordLogin.setText("");
                                        dialog.dismiss();
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        } catch (Exception ex) {
                            Log.e("ERROR" , ex.getMessage().toString());
                        }

                    }
                });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}