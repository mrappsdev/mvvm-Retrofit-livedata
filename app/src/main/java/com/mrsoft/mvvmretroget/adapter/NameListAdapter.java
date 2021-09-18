package com.mrsoft.mvvmretroget.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mrsoft.mvvmretroget.MainActivity;
import com.mrsoft.mvvmretroget.R;
import com.mrsoft.mvvmretroget.model.NameListModel;

import java.util.List;

public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.MyViewHolder> {

    private Context context;
    private List<NameListModel> nameList;
    //private AdapterView.OnItemClickListener clickListener;
    private  ItemClickListener clickListener;

    public NameListAdapter(Context context, List<NameListModel> nameList, ItemClickListener clickListener) {
        this.context = context;
        this.nameList = nameList;
        this.clickListener = clickListener;
    }


    public void setNameList(List<NameListModel> nameList){
        this.nameList = nameList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NameListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.number.setText(this.nameList.get(position).getPhone());
        holder.tvTitle.setText(this.nameList.get(position).getName().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onNameClick(nameList.get(position));
            }
        });
       /* Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        if(this.nameList != null) {
            return this.nameList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView number;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView)itemView.findViewById(R.id.titleView);
            number = (TextView) itemView.findViewById(R.id.Number);
        }
    }
    public interface ItemClickListener{
        public void onNameClick(NameListModel name);
    }
}
