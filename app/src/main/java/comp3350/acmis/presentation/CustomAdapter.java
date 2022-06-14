package comp3350.acmis.presentation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.objects.Booking;

public class CustomAdapter extends BaseAdapter {

    private ManageFragment mContext;
    private ArrayList<Booking> myBookings;


    public CustomAdapter(ManageFragment context, ArrayList<Booking> userBookings) {
        mContext = context;
        myBookings = userBookings;
    }


    @Override
    public int getCount() {
        return myBookings.size();
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
        title1.setText(myBookings.get(position).getRoute().get(0).getSource().getCity());
        title2.setText(myBookings.get(position).getRoute().get(0).getDestination().getCity());
        i1.setImageResource(R.drawable.airplane_symbol);

        return (row);
    }
}