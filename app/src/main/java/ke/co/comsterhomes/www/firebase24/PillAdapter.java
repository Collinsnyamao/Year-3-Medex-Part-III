package ke.co.comsterhomes.www.firebase24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by collinsnyamao on 12/1/17.
 */

public class PillAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private ArrayList<Pills> mDataSource;


    public PillAdapter(Context context,ArrayList<Pills> items){

        mcontext = context;
        mDataSource = items;
        layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View rowview = layoutInflater.inflate(R.layout.list_items_labels, parent, false);

        TextView titletextview = (TextView) rowview.findViewById(R.id.recipe_list_title);
        TextView subtitletextview = (TextView) rowview.findViewById(R.id.recipe_list_subtitle);
        TextView detailtextview = (TextView) rowview.findViewById(R.id.recipe_list_detail);
        /*ImageView thumbnailimageview = (ImageView) rowview.findViewById(R.id.recipe_list_thumbnail);*/

        Labels recipe = (Labels) getItem(position);
        titletextview.setText(recipe.description);
        subtitletextview.setText(recipe.description);
        detailtextview.setText(recipe.description);
        /*Picasso.with(mcontext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailimageview);*/

        return rowview;
    }
}
