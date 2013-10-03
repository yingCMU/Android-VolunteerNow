package edu.cmu.vn3;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.setDisplayShowHomeEnabled(false); 
		actionBar.setDisplayShowTitleEnabled(false);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
//		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			Tab tVN = actionBar.newTab();
			tVN.setIcon(R.drawable.vn);
			tVN.setTabListener(this);
			actionBar.addTab(tVN);

			Tab tEv = actionBar.newTab();
			tEv.setIcon(R.drawable.events_b);
			tEv.setTabListener(this);
			actionBar.addTab(tEv);
			
			Tab tUser = actionBar.newTab();
			tUser.setIcon(R.drawable.user_b);
			tUser.setTabListener(this);
			actionBar.addTab(tUser);
			
			Tab tMes = actionBar.newTab();
			tMes.setIcon(R.drawable.mess_b);
			tMes.setTabListener(this);
			actionBar.addTab(tMes);
			
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	private class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			if(position == 1){
				fragment = new EventsFragment(); 
			}else{
				fragment = new DummySectionFragment();
			}
			
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section3).toUpperCase();
			case 3:
				return getString(R.string.title_section4).toUpperCase();
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}
	
	private class EventsTab extends Tab{

		@Override
		public CharSequence getContentDescription() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public View getCustomView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Drawable getIcon() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getPosition() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getTag() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CharSequence getText() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void select() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Tab setContentDescription(int resId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setContentDescription(CharSequence contentDesc) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setCustomView(View view) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setCustomView(int layoutResId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setIcon(Drawable icon) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setIcon(int resId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setTabListener(TabListener listener) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setTag(Object obj) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setText(CharSequence text) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Tab setText(int resId) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
