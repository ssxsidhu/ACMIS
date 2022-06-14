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

    private FragmentManage mContext;
    private ArrayList<Booking> myBookings;


    public CustomAdapter(FragmentManage context, ArrayList<Booking> userBookings) {
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
        TextView titleTopLeft,titleTopRight,titleMiddleLeft,titleMiddleRight,titleBottomLeft,titleBottomRight;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.list_icon);
        titleTopLeft = (TextView) row.findViewById(R.id.list_top_text_left);
        titleTopRight = (TextView) row.findViewById(R.id.list_top_text_right);
        titleMiddleLeft = (TextView) row.findViewById(R.id.list_middle_text_left);
        titleMiddleRight = (TextView) row.findViewById(R.id.list_middle_text_right);
        titleBottomLeft = (TextView) row.findViewById(R.id.list_bottom_text_left);
        titleBottomRight = (TextView) row.findViewById(R.id.list_bottom_text_right);

        //top is temporary
        titleTopLeft.setText("12:00");
        titleTopRight.setText("15:00");
        titleMiddleLeft.setText(myBookings.get(position).getRoute().get(0).getSource().getCity());
        titleMiddleRight.setText(myBookings.get(position).getRoute().get(0).getDestination().getCity());
        //bottom Left is temporary
        titleBottomLeft.setText("June 15");
        titleBottomRight.setText(String.format("FlightID: #%d", myBookings.get(position).getRoute().get(0).getFlightID()));
        i1.setImageResource(R.drawable.airplane_symbol);

        return (row);
    }
}