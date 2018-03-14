package familyfriday.apartmentlist.com.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import familyfriday.apartmentlist.com.familyfriday.R;


public class SimpleSpinnerAdaptor extends ArrayAdapter<String>{

	ArrayList<String> groupType =null;
	Context mContext=null;
	private LayoutInflater inflater=null;
	
public SimpleSpinnerAdaptor(Context context, int textViewResourceId, ArrayList<String> list) {
	super(context,textViewResourceId, list);
	this.mContext=context;
	this.groupType =list;
	inflater = LayoutInflater.from(mContext);
}

	public View getView(int position, View convertView, ViewGroup parent) {

			convertView = inflater.inflate(R.layout.my_spinner_style, parent,false);
	        TextView make = (TextView) convertView.findViewById(R.id.textview_spinnerTarget);
	        make.setText(groupType.get(position));
	        return convertView;
	    }


	public View getDropDownView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = LayoutInflater.from(mContext);
		convertView = inflater.inflate(R.layout.my_spinner_style, parent, false);
		TextView make = (TextView) convertView.findViewById(R.id.textview_spinnerTarget);


		make.setText(groupType.get(position));
		return convertView;
	   }


}
