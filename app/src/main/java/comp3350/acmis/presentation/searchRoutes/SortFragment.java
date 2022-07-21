package comp3350.acmis.presentation.searchRoutes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import comp3350.acmis.R;

public class SortFragment extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.sort_bottom_sheet_layout,
                container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        ListView sortListView = view.findViewById(R.id.list_sort_options);
        String[] sortOptions = {"Lowest price","Number of stops","Earliest departure time","Earliest Arrival time"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),R.layout.sort_card_view,R.id.sort_text_view,sortOptions);
        sortListView.setAdapter(adapter);
    }

}
