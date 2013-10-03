package edu.cmu.vn3;

import edu.cmu.vn3.entities.Filters;
import android.app.Activity;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;


public class EventsFragment extends Fragment implements OnTouchListener, OnClickListener, OnFiltersScreenCloseListener{

	Activity _activity;
	
	LinearLayout nearbyLayout, locationLayout, timeLayout;
	LinearLayout currentLayout;
	LinearLayout searchLayout;
	boolean sortingDesc;
	ToggleButton btnFilter, btnSearch;
	
	// Create and show the dialog.
    DialogFragment filtersFragment;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		_activity = activity;
	}

	ViewGroup _view;
	static LayoutInflater _li;
	//private MapView myOpenMapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		filtersFragment = MyFiltersFragment.newInstance(_activity, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		_li = inflater;
		_view = (LinearLayout)inflater.inflate(R.layout.events, container, false);
		nearbyLayout = (LinearLayout) _view.findViewById(R.id.nearbyLayout);
		nearbyLayout.setOnTouchListener(this);
		locationLayout = (LinearLayout) _view.findViewById(R.id.locationLayout);
		locationLayout.setOnTouchListener(this);
		timeLayout = (LinearLayout) _view.findViewById(R.id.timeLayout);
		timeLayout.setOnTouchListener(this);
		btnFilter = (ToggleButton) _view.findViewById(R.id.btnFilter);
		btnFilter.setOnClickListener(this);
		btnSearch = (ToggleButton) _view.findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(this);
		
		searchLayout = (LinearLayout) _view.findViewById(R.id.searchLayout);
		
		timeLayout.setOnTouchListener(this);
		
		currentLayout = nearbyLayout;
		sortingDesc = true;
		return _view;
	}
	
	public void moveFocus(View currentFocus, View newFocus){
		int sdk = android.os.Build.VERSION.SDK_INT;
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			currentFocus.setBackgroundDrawable(getResources().getDrawable(R.color.light_gray));
			newFocus.setBackgroundDrawable(getResources().getDrawable(android.R.color.background_light));
		} 
		
		ImageView iv ;
		if(newFocus != currentFocus){
			sortingDesc = true;
			iv = (ImageView)currentFocus.findViewById(R.id.imageView);
			iv.setImageDrawable(getResources().getDrawable(R.drawable.radiob));
			
			iv = (ImageView)newFocus.findViewById(R.id.imageView);
			iv.setImageDrawable(getResources().getDrawable(R.drawable.radiod));
		}else{
			if(sortingDesc){
				iv = (ImageView)newFocus.findViewById(R.id.imageView);
				iv.setImageDrawable(getResources().getDrawable(R.drawable.radiou));	
				sortingDesc = false;
			}else{
				iv = (ImageView)newFocus.findViewById(R.id.imageView);
				iv.setImageDrawable(getResources().getDrawable(R.drawable.radiod));
				sortingDesc = true;
			}
		}
		currentLayout = (LinearLayout)newFocus;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		moveFocus(currentLayout, v);
		return false;
	}


	@Override
	public void onClick(View v) {
		if(v==btnFilter){
			btnFilter.setChecked(true);
			showFiltersScreen();
		}else if(v==btnSearch){
			if(btnSearch.isChecked()){
				searchLayout.setVisibility(View.VISIBLE);
			}else{
				searchLayout.setVisibility(View.GONE);
			}
		}
	}
	
	void showFiltersScreen() {

	    // DialogFragment.show() will take care of adding the fragment
	    // in a transaction.  We also want to remove any currently showing
	    // dialog, so make our own transaction and take care of that here.
		
	    FragmentTransaction ft = getFragmentManager().beginTransaction();
	    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
	    if (prev != null) {
	        ft.remove(prev);
	    }
	    ft.addToBackStack(null);

	    filtersFragment.setShowsDialog(true);
	    filtersFragment.show(ft, "dialog");
	}
	
	public void onCloseFilterScreen(Filters f){
		if(f!=null){
			btnFilter.setChecked(true);
		}else{
			btnFilter.setChecked(false);
		}
	}
	
	
	
///////////////////////////////Filters Screen///////////////////////////////////////////////
	
	private static class MyFiltersFragment extends DialogFragment implements OnClickListener{

		static ToggleButton [] tbRadius = new ToggleButton[3];
		static ToggleButton [] tbTime= new ToggleButton[3];
		
		static Button btFilter, btNoFilter;
		
		static ToggleButton tbCurrentRadius, tbCurrentTime;
		
		static Activity dialogActivity;
		static Filters f;
		static OnFiltersScreenCloseListener listener;
		static Spinner spinner;
	    /**
	     * Create a new instance of MyDialogFragment, providing "num"
	     * as an argument.
	     */
	    static MyFiltersFragment newInstance(Activity activity, OnFiltersScreenCloseListener l) {
	        MyFiltersFragment f = new MyFiltersFragment();
	        dialogActivity = activity;
	        listener = l;
	        return f;
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setStyle(STYLE_NO_TITLE, 0); // remove title from dialogfragment
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	    	
	    	View popupView = (LinearLayout)_li.inflate(R.layout.filter_options, null, false);
			
	    	spinner = (Spinner) popupView.findViewById(R.id.puto_spinner);
	    	
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(dialogActivity, R.array.causes, 
					android.R.layout.simple_spinner_item);
			
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
	    	
			tbRadius[0] = (ToggleButton) popupView.findViewById(R.id.radius1);
			tbRadius[0].setOnClickListener(this);
			tbRadius[1] = (ToggleButton) popupView.findViewById(R.id.radius2);
			tbRadius[1].setOnClickListener(this);
			tbRadius[2] = (ToggleButton) popupView.findViewById(R.id.radius3);
			tbRadius[2].setOnClickListener(this);
			
			tbTime[0] = (ToggleButton) popupView.findViewById(R.id.time1);
			tbTime[0].setOnClickListener(this);
			tbTime[1] = (ToggleButton) popupView.findViewById(R.id.time2);
			tbTime[1].setOnClickListener(this);
			tbTime[2] = (ToggleButton) popupView.findViewById(R.id.time3);
			tbTime[2].setOnClickListener(this);
			
			btFilter = (Button) popupView.findViewById(R.id.btnFilter);
			btFilter.setOnClickListener(this);
			btNoFilter = (Button) popupView.findViewById(R.id.btnNoFilter);
			btNoFilter.setOnClickListener(this);
			
	        return popupView;
	    }

		@Override
		public void onClick(View v) {
			if(v == btFilter){
				listener.onCloseFilterScreen(new Filters(0, spinner.getSelectedItem().toString(), 0, 0));
				dismiss();
			}else if(v == btNoFilter){
				listener.onCloseFilterScreen(null);
				dismiss();
			}else{
				for(int i=0; i<tbRadius.length; i++){
					if(tbRadius[i] == v){
						switchRadius(v);
						return;
					}
				}
				for(int i=0; i<tbTime.length; i++){
					if(tbTime[i] == v){
						switchTime(v);
						return;
					}
				}
			}
			
		}

		private void switchRadius(View v) {
			if(tbCurrentRadius != null && tbCurrentRadius == v){
				((ToggleButton)v).setSelected(false);
			}else{
				for(int i=0; i<tbRadius.length; i++){
					if(v == tbRadius[i]){
						tbRadius[i].setChecked(true);
						tbCurrentRadius = tbRadius[i]; 
					}else{
						tbRadius[i].setChecked(false);
					}
				}
			}
		}

		private void switchTime(View v) {
			if(tbCurrentTime != null && tbCurrentTime == v){
				((ToggleButton)v).setSelected(false);
			}else{
				for(int i=0; i<tbTime.length; i++){
					if(v == tbTime[i]){
						tbTime[i].setChecked(true);
						tbCurrentTime = tbTime[i]; 
					}else{
						tbTime[i].setChecked(false);
					}
				}
			}
			
		}
	}
	
	
	

}
