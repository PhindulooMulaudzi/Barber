package com.phindulo.barber;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QueueRecyclerViewAdapter extends RecyclerView.Adapter<QueueRecyclerViewAdapter.ViewHolder> {
    private List<QueueListItem> queueListItemList;
    private Context context;

    public QueueRecyclerViewAdapter(List<QueueListItem> queueListItemList, Context context) {
        this.queueListItemList = queueListItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_queue_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QueueListItem queueListItem = queueListItemList.get(position);

        holder.name.setText(queueListItem.getName());
        holder.position.setText(queueListItem.getPosition());
        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(queueListItem.getTimestamp().getSeconds() * 1000);
        holder.timeStamp.setText(timeAgo);
    }

    @Override
    public int getItemCount() {
        return queueListItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, position, timeStamp;

        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.queue_list_name);
            position = itemView.findViewById(R.id.queue_list_position);
            timeStamp = itemView.findViewById(R.id.queue_list_timestamp);
        }
    }
}
