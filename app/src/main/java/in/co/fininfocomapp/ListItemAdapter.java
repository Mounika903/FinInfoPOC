package in.co.fininfocomapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{
    private ArrayList<DataModal> listItems;

    public ListItemAdapter(ArrayList<DataModal> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataModal myListData = listItems.get(position);
        holder.name.setText(myListData.getName());
        holder.age.setText(String.valueOf(myListData.getAge()));
        holder.city.setText(myListData.getCity());

    }

    @Override
    public int getItemCount() {
        Log.println(Log.DEBUG,"List length+", String.valueOf(listItems.size()));
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView city;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.age = (TextView) itemView.findViewById(R.id.age);
            this.city = (TextView) itemView.findViewById(R.id.city);
        }
    }
}