package ke.co.comsterhomes.www.firebase24;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    TextView text1;
    TextView logintextview,Devtry1;
    Intent signupintent,logInIntent;
    ListView mlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupintent = new Intent(MainActivity.this,SignUp.class);
        logInIntent = new Intent(MainActivity.this,LoginActivity.class);

        logintextview = (TextView) findViewById(R.id.txttLogin);
        logintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(logInIntent);
            }
        });
        text1 = (TextView) findViewById(R.id.txtt1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signupintent);
            }
        });
        mlistView = (ListView) findViewById(R.id.mlistview);

        getSupportActionBar().hide();

        final ArrayList<Labels> labellist = Labels.getLabelFromFile("Pills.json",this);
        final String[] listitems = new String[labellist.size()];

        for (int i=0; i <labellist.size();i++){
            Labels recipe = labellist.get(i);
            listitems[i] = recipe.description;
        }

        LabelAdapter recipeAdapter = new LabelAdapter(this, labellist);
        mlistView.setAdapter(recipeAdapter);


        final Context context = this;
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Labels selectedRecipe = labellist.get(position);
                Intent detailintent = new Intent(MainActivity.this,Detail_webview.class);
                startActivity(detailintent);
            }

            /*public void OnItemClick(AdapterView<?> parent , int position , View view, long id){

            }*/
        });

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
