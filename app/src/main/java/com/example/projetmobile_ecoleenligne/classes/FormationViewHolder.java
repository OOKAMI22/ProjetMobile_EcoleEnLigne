package com.example.projetmobile_ecoleenligne.classes;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile_ecoleenligne.R;

public class FormationViewHolder extends   RecyclerView.ViewHolder{

    TextView titreView;
    TextView descriptionView;
    TextView dureeView;
    TextView prixView;

    // @itemView: recyclerview_item_layout.xml
    public FormationViewHolder(@NonNull View itemView) {
        super(itemView);


        this.titreView =  itemView.findViewById(R.id.titre);
        this.descriptionView = itemView.findViewById(R.id.description);
        this.dureeView =  itemView.findViewById(R.id.duree);
        this.prixView =  itemView.findViewById(R.id.prix);
    }


}
