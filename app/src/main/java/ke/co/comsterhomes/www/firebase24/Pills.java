package ke.co.comsterhomes.www.firebase24;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by collinsnyamao on 12/1/17.
 */

public class Pills {
    public String description;

    public static ArrayList<Pills> getPillFromFile(String filename, Context context){
        final ArrayList<Pills> pillList = new ArrayList<>();

        try{
            String jsonString = loadJsonFromAsset("Pills2.json",context);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("");
            for (int i = 0;i<jsonArray.length();i++){

                Pills pill = new Pills();
                //Log.e("Labels",""+jsonArray.getJSONObject(i).toString());
                pill.description = jsonArray.get(i).toString();

                pillList.add(pill);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return pillList;
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
