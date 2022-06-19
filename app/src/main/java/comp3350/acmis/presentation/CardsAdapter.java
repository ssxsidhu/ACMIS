package comp3350.acmis.presentation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import comp3350.acmis.R;
import comp3350.acmis.objects.Booking;
import comp3350.acmis.objects.Route;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.Viewholder> {

    private FragmentManage context;
    private ArrayList<Booking> displayList;
    CustomAdapter customAdapter;
    //temporary

    // Constructor
    public CardsAdapter(FragmentManage context, ArrayList<Booking> userBookings) {
        this.context = context;
        displayList = userBookings;
        customAdapter = new CustomAdapter(context);
    }

    @NonNull
    @Override
    public CardsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        String uri = "@mipmap/"+displayList.get(position).getRoute().getRoute().get(0).getDestination().getCity().toLowerCase(Locale.ROOT);
        int imageResource = context.getResources().getIdentifier(uri, null, context.requireActivity().getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.imageView.setImageDrawable(res);
//        holder.imageView.setImageResource(R.drawable.locationName);
        customAdapter.setDisplayList(displayList.get(position));
        holder.listView.setAdapter(customAdapter);

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return displayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ListView listView;
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            listView = itemView.findViewById(R.id.card_view_list);
        }
    }
}
