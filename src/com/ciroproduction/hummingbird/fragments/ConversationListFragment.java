package com.ciroproduction.hummingbird.fragments;

import com.ciroproduction.hummingbird.MainActivity;
import com.ciroproduction.hummingbird.R;
import com.ciroproduction.hummingbird.R.id;
import com.ciroproduction.hummingbird.R.layout;
import com.ciroproduction.hummingbird.fragments.ContactMenuFragment.ContactAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ConversationListFragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		String[] colors = getResources().getStringArray(R.array.color_names);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < colors.length; i++) {
			adapter.add(new SampleItem("Contact Name "+colors[i], android.R.drawable.sym_def_app_icon));
		}
		setListAdapter(adapter);
	}

	/** --------------------------------------*/
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new ConversationFragment(R.color.red);
			break;
		case 1:
			newContent = new ConversationFragment(R.color.green);
			break;
		case 2:
			newContent = new ConversationFragment(R.color.blue);
			break;
		case 3:
			newContent = new ConversationFragment(android.R.color.white);
			break;
		case 4:
			newContent = new ConversationFragment(android.R.color.black);
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		} /**else if (getActivity() instanceof ResponsiveUIActivity) {
			ResponsiveUIActivity ra = (ResponsiveUIActivity) getActivity();
			ra.switchContent(fragment);
		}*/
	}
	/** --------------------------------------*/
	
	
	
	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_conversation, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
