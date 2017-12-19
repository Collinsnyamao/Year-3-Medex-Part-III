package ke.co.comsterhomes.www.firebase24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    FirebaseAuth firebaseAuth;
    Button logout;
    FirebaseUser firebaseUser;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textView = (TextView) findViewById(R.id.dbtest1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(HomeActivity.this,WebMD.class);
                startActivity(main);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        webView = (WebView) findViewById(R.id.webv1);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.webmd.com/drugs/2/index");

        logout = (Button) findViewById(R.id.lgt);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                if (firebaseAuth.getCurrentUser()==null){
                    textView.setText("Logged Out");
                }
            }
        });

        String uname = firebaseAuth.getCurrentUser().toString();


        firebaseUser = firebaseAuth.getCurrentUser();

        String userEmail = firebaseUser.getEmail();

        String userEmail2 = userEmail;

        int index = userEmail2.indexOf('@');
        userEmail2 = userEmail2.substring(0,index);
        textView.setText(userEmail2);

    }
}
