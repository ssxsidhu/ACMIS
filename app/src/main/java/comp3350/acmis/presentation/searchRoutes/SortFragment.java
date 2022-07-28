package comp3350.acmis.presentation.searchRoutes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import comp3350.acmis.R;
import comp3350.acmis.business.SortRoutes;
import comp3350.acmis.objects.Route;
import comp3350.acmis.presentation.Messages;

public class SortFragment extends BottomSheetDialogFragment {

    private ListView sortListView;
    private ArrayList<Route> routeList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort_bottom_sheet_layout,
                container, false);

        sortListView = view.findViewById(R.id.list_sort_options);
        String[] sortOptions = {"Lowest price","Number of stops","Earliest departure time","Least duration time"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),R.layout.sort_card_view,R.id.sort_text_view,sortOptions);
        sortListView.setAdapter(adapter);
        routeList = (ArrayList<Route>) getArguments().getSerializable("availableRouteList");

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        SortRoutes sortedRoutes = new SortRoutes();
        sortListView.setOnItemClickListener((adapterView, view1, i, l) -> {

            switch (i) {
                case 0:
                    sortedRoutes.lowestPrice(routeList);
                    break;
                case 1:
                    sortedRoutes.leastStops(routeList);
                    break;
                case 2:
                    sortedRoutes.earliestDepart(routeList);
                    break;
                case 3:
                    sortedRoutes.lowestDuration(routeList);
                    break;
            }

            Messages.makeToast(requireContext().getApplicationContext(), "Flights Sorted");
            ((SearchResults) requireActivity()).onResume();
            getParentFragmentManager().beginTransaction().remove(SortFragment.this).commit();
        });


    }


}
