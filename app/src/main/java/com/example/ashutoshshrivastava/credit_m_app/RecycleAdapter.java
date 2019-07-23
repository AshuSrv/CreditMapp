package com.example.ashutoshshrivastava.credit_m_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csa on 3/1/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Myholder> {
    List<DataModel> dataModelArrayList;
    Context context;
    public RecycleAdapter(List<DataModel> dataModelArrayList,Context context) {
        this.dataModelArrayList = dataModelArrayList;
        this.context=context;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView name,city,credit;
        RelativeLayout parentLayout;

        public Myholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name1);
            city = (TextView) itemView.findViewById(R.id.city1);
            credit = (TextView) itemView.findViewById(R.id.country1);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder,final int position) {
        final DataModel dataModel=dataModelArrayList.get(position);
        holder.name.setText(dataModel.getName());
        final String name=dataModelArrayList.get(position).name;
        final String city=dataModelArrayList.get(position).city;
        final String credit=String.valueOf(dataModelArrayList.get(position).credit);
        final String comapny=dataModelArrayList.get(position).company;
        holder.city.setText(dataModel.getCity());
        holder.credit.setText(String.valueOf(dataModel.getCredit()));



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ShowUser.class);
                intent.putExtra("name", name);
                intent.putExtra("city", city);
                intent.putExtra("credit", credit);
                intent.putExtra("company", comapny);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}
