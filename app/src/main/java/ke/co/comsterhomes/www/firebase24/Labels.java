package ke.co.comsterhomes.www.firebase24;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by collinsnyamao on 11/30/17.
 */

public class Labels {

    public String description;

    public static ArrayList<Labels> getLabelFromFile(String filename, Context context){
        final ArrayList<Labels> labelList = new ArrayList<>();

        try{
            String jsonString = loadJsonFromAsset("Pills.json",context);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("drugs");
            for (int i = 0;i<jsonArray.length();i++){

                Labels label = new Labels();
                //Log.e("Labels",""+jsonArray.getJSONObject(i).toString());
                label.description = jsonArray.get(i).toString();

                labelList.add(label);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return labelList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
