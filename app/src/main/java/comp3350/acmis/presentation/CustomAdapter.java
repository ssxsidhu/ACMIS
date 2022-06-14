package comp3350.acmis.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.acmis.R;

public class CustomAdapter extends BaseAdapter {

    private ManageFragment mContext;
    private String[]  Title;
    private int[] imge;

    public CustomAdapter(ManageFragment context, String[] text1, int[] imageIds) {
        mContext = context;
        Title = text1;
        imge = imageIds;
    }


    @Override
    public int getCount() {
        return Title.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        TextView title1,title2;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.imgIcon);
        title1 = (TextView) row.findViewById(R.id.txtTitle1);
        title2 = (TextView) row.findViewById(R.id.txtTitle2);
        title1.setText(Title[position]);
        title2.setText(Title[position]);
        i1.setImageResource(imge[position]);

        return (row);
    }
}