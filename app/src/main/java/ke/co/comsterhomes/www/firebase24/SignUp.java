package ke.co.comsterhomes.www.firebase24;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener  {
    private FirebaseAuth firebaseAuth;
    GoogleApiClient mGoogleApiClient;
    EditText emailtextsignup,passwordtextsignup;
    Button manualsignupbtn;
    private ProgressDialog progressDialog1;
    private static final String TAG = "Signup Activity";
    TextView text1;
    SignInButton googlesigninbutton;
    Button signoutbtn;
    private ProgressDialog progressDialog;
    private static final int RC_SIGN_IN = 9001;
    private EditText emailText,passwordText;
    Intent signupintent;
    Intent homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        progressDialog = new ProgressDialog(this);


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();
        text1 = (TextView) findViewById(R.id.statustxt);
        googlesigninbutton = (SignInButton) findViewById(R.id.signingooglebtn);
        googlesigninbutton.setOnClickListener(this);
        signoutbtn = (Button) findViewById(R.id.susignupbtn);
        signoutbtn.setOnClickListener(this);

        emailText = (EditText) findViewById(R.id.signupemail);
        passwordText = (EditText) findViewById(R.id.signuppassword);

        firebaseAuth = FirebaseAuth.getInstance();
        homeIntent = new Intent(SignUp.this,MainActivity.class);

    }



    private void registerUser(){
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"The email field is empty",Toast.LENGTH_SHORT).show();
            return;

        }if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter a Password!",Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this,"User successfully registered",Toast.LENGTH_SHORT).show();
                    startActivity(homeIntent);
                }else {
                    Toast.makeText(SignUp.this,"User Not registered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.susignupbtn:
                registerUser();
                break;
            case R.id.signingooglebtn:
                signIn();
                break;
        }

    }

    private void signIn() {
        Intent signinIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signinIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG,"onConnectionFailed: " + connectionResult);
    }
}
