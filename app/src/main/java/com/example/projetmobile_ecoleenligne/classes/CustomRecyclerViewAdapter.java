package com.example.projetmobile_ecoleenligne.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile_ecoleenligne.FormationsActivity;
import com.example.projetmobile_ecoleenligne.R;

import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<FormationViewHolder>{
    private final List<Formation> formations;
    private List<Formation> formation;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public CustomRecyclerViewAdapter(Context context, List<Formation> datas ) {
        this.context = context;
        this.formations = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public FormationViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.list_item_formation, parent, false);

        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecyclerItemClick( (RecyclerView)parent, v);
            }
        });
        return new FormationViewHolder(recyclerViewItem);
    }


    @Override
    public void onBindViewHolder(FormationViewHolder holder, int position) {
        // Cet country in countries via position
        Formation formation = this.formations.get(position);

        // Bind data to viewholder

        holder.titreView.setText(formation.getTitre());
        holder.descriptionView.setText("Description : " + formation.getDescription());
        holder.dureeView.setText("Duree : " + formation.getDuree()+" jours");
        holder.prixView.setText("prix : " + formation.getPrix()+" €");
    }

    @Override
    public int getItemCount() {
        return this.formations.size();
    }

    // Find Image ID corresponding to the name of the image (in the directory drawable).
    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i(FormationsActivity.LOG_TAG, "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Formation country  = this.formations.get(itemPosition);

        Toast.makeText(this.context, country.getTitre(), Toast.LENGTH_LONG).show();
    }
}
