package ke.co.comsterhomes.www.firebase24;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mref = database.getReference("prescription");

    private Button mSendData;
    private DatabaseReference mDatabase;
    private EditText mDrug_name;
    private EditText mDrug_frequency;
    private EditText mDrug_quantity;
    private EditText mReminder_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_input);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("prescription");
        mDrug_name=(EditText) findViewById(R.id.Drug_name);
        mDrug_frequency=(EditText) findViewById(R.id.Drug_frequency);
        mDrug_quantity=(EditText) findViewById(R.id.Drug_quantity);
        mReminder_note=(EditText) findViewById(R.id.Remainder_note);



        mSendData=(Button) findViewById(R.id.sendData);

        mSendData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //create child in root project and assign value to the child
                String name=mDrug_name.getText().toString().trim();

                String frequency=mDrug_frequency.getText().toString().trim();
                String quantity=mDrug_quantity.getText().toString().trim();
                String reminder=mReminder_note.getText().toString().trim();

                HashMap<String, String>dataMap=new HashMap<String,String>();
                dataMap.put("Drug_name",name);
                dataMap.put("Drug_frequency",frequency);
                dataMap.put("Drug_quantity",quantity);
                dataMap.put("reminder",reminder);
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "stored...", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error.....", Toast.LENGTH_LONG).show();
                        }
                    }
                });




            }
        });




    }



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
    }
}