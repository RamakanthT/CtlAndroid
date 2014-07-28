package com.example.test;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class YourAdapter extends BaseAdapter {

    Context context;
    String[] data;
    ArrayList<PoolBean> poolAl;
    private static LayoutInflater inflater = null;

    public YourAdapter(Context context, ArrayList<PoolBean> poolAl) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.poolAl = poolAl;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return poolAl.size();
    	
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return poolAl.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        
        TextView pnameTv = (TextView) vi.findViewById(R.id.pseats);
        TextView pfromTv = (TextView) vi.findViewById(R.id.pfrom);
        TextView ptoTv = (TextView) vi.findViewById(R.id.pto);
        TextView pviaTv = (TextView) vi.findViewById(R.id.pvia);
        TextView pdateTv = (TextView) vi.findViewById(R.id.pdate);
        TextView ptimeTv = (TextView) vi.findViewById(R.id.ptime);
        
        PoolBean poolObj=poolAl.get(position);

        pnameTv.setText(poolObj.getPseats());
        pfromTv.setText(poolObj.getPfrom());
        ptoTv.setText(poolObj.getPto());
        pviaTv.setText(poolObj.getPvia());
        pdateTv.setText(poolObj.getPdate());
        ptimeTv.setText(poolObj.getPtime());
        
        return vi;
    }

	
}
