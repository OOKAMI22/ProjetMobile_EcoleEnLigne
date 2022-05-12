package com.example.projetmobile_ecoleenligne.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.projetmobile_ecoleenligne.R;

import java.util.List;

public class CustomListAdapterExamen extends BaseAdapter {
    private List<Examen> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapterExamen(Context aContext, List<Examen> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomListAdapterFormation.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_examen, null);
            holder = new CustomListAdapterFormation.ViewHolder();
            //holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.titreView = (TextView) convertView.findViewById(R.id.titre);
            convertView.setTag(holder);
        } else {
            holder = (CustomListAdapterFormation.ViewHolder) convertView.getTag();
        }

        Examen examen = this.listData.get(position);
        holder.titreView.setText(examen.getTitre());


        //int imageId = this.getMipmapResIdByName(formation.getTitre());

        //holder.imageView.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        //ImageView imageView;
        TextView titreView;
    }

}
