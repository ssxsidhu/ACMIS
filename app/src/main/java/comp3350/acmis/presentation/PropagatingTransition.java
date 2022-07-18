package comp3350.acmis.presentation;

import android.graphics.Interpolator;
import android.graphics.Rect;
import android.transition.CircularPropagation;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionPropagation;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.acmis.R;

public class PropagatingTransition {
    private ViewGroup sceneRoot;
    private View startingView ;
    private Transition transition ;
    private ArrayList<View> targets = new ArrayList<>();
    private long duration = 1000;
    private TransitionPropagation propagation =  new CircularPropagation();

    public PropagatingTransition(ViewGroup sceneRoot , View startingView, Transition transition){
        for(int i = 0; i<sceneRoot.getChildCount();i++){
            targets.add(sceneRoot.getChildAt(i));
        }

        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        transition.setDuration(duration);
        transition.setPropagation(propagation);
        this.sceneRoot=sceneRoot;
        this.startingView = startingView;
        this.transition = transition;

    }

    public void start(){
        if(startingView == null && sceneRoot.getChildCount()>0){
            startingView = sceneRoot.getChildAt(0);
        }

        Rect viewRect = new Rect();
        startingView.getGlobalVisibleRect(viewRect);
        transition.setEpicenterCallback(new Transition.EpicenterCallback() {

            @Override
            public Rect onGetEpicenter(Transition transition) {
                return viewRect;
            }
        });

        for(int i=0;i<targets.size();i++){
            targets.get(i).setVisibility(View.INVISIBLE);
        }

        TransitionManager.beginDelayedTransition(sceneRoot,transition);

        for(int i=0;i<targets.size();i++){
            if(targets.get(i).getId()!= R.id.book_button && targets.get(i).getId()!= R.id.no_results_found)
            targets.get(i).setVisibility(View.VISIBLE);
        }

    }

}
