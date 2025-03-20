package com.salazarisaiahnoel.monay.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salazarisaiahnoel.monay.R;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxHolder> {

    List<String> title, description;

    public InboxAdapter(List<String> title, List<String> description) {
        this.title = title;
        this.description = description;
    }

    @NonNull
    @Override
    public InboxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_item, parent, false);
        return new InboxHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxHolder holder, int position) {
        holder.title.setText(title.get(holder.getAdapterPosition()));
        holder.description.setText(description.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    class InboxHolder extends RecyclerView.ViewHolder{

        TextView title, description;

        public InboxHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.inbox_title);
            description = itemView.findViewById(R.id.inbox_description);

            title.setSelected(true);
        }
    }
}
