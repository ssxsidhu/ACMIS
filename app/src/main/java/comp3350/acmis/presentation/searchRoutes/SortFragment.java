package comp3350.acmis.presentation.searchRoutes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.objects.Route;

public class SortFragment extends BottomSheetDialogFragment {

    private ListView sortListView;
    private ArrayList<Route> routeList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sort_bottom_sheet_layout,
                container, false);

        sortListView = view.findViewById(R.id.list_sort_options);
        String[] sortOptions = {"Lowest price","Number of stops","Earliest departure time","Earliest Arrival time"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),R.layout.sort_card_view,R.id.sort_text_view,sortOptions);
        sortListView.setAdapter(adapter);
        routeList = (ArrayList<Route>) getArguments().getSerializable("availableRouteList");

        return view ;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 :
                }
            }
        });


    }



}
