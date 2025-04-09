package com.salazarisaiahnoel.monay.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.salazarisaiahnoel.monay.R;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxHolder> {

    List<String> title, description, date;
    InboxItemOnClick onClick;

    public InboxAdapter(List<String> title, List<String> description, List<String> date, InboxItemOnClick onClick) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public InboxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_item, parent, false);
        return new InboxHolder(v, onClick);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxHolder holder, int position) {
        holder.title.setText(title.get(holder.getAdapterPosition()));
        holder.description.setText(description.get(holder.getAdapterPosition()));
        holder.date.setText(date.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public interface InboxItemOnClick{
        void click(int position);
    }

    class InboxHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ConstraintLayout inbox_item_container;
        TextView title, description, date;
        InboxItemOnClick onClick;

        public InboxHolder(@NonNull View itemView, InboxItemOnClick onClick) {
            super(itemView);

            inbox_item_container = itemView.findViewById(R.id.inbox_item_container);
            title = itemView.findViewById(R.id.inbox_title);
            description = itemView.findViewById(R.id.inbox_description);
            date = itemView.findViewById(R.id.inbox_date);

            this.onClick = onClick;
            inbox_item_container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClick.click(getAdapterPosition());
        }
    }
}
