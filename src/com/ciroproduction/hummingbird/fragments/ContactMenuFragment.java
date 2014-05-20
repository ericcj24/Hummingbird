package com.ciroproduction.hummingbird.fragments;

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

import com.ciroproduction.hummingbird.MainActivity;
import com.ciroproduction.hummingbird.R;


public class ContactMenuFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] colors = getResources().getStringArray(R.array.color_names);
		
		ContactAdapter adapter = new ContactAdapter(getActivity());
		for (int i = 0; i < colors.length; i++) {
			adapter.add(new ContactItem(colors[i], android.R.drawable.sym_def_app_icon));
		}
		setListAdapter(adapter);

	}

	@Override
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
	
	private class ContactItem {
		public String tag;
		public int iconRes;
		public ContactItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class ContactAdapter extends ArrayAdapter<ContactItem> {

		public ContactAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_contact, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_contact_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}


}
