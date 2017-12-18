package ke.co.comsterhomes.www.firebase24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    FirebaseAuth firebaseAuth;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textView = (TextView) findViewById(R.id.dbtest1);
        firebaseAuth = FirebaseAuth.getInstance();

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
        textView.setText(uname);

    }
}
