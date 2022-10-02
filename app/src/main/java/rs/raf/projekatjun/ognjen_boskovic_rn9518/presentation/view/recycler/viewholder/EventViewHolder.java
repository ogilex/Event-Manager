package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private ImageView delete;
    private Context context;

    public EventViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onDeleteClicked, Function<Integer, Void> onItemClicked) {
        super(itemView);

        this.context = context;
        delete = itemView.findViewById(R.id.deleteIvEventItem);

        delete.setOnClickListener(v -> {
            onDeleteClicked.apply(getAdapterPosition());
        });
        itemView.setOnClickListener(v -> {
            onItemClicked.apply(getAdapterPosition());
        });
    }

    public void bind(Event event){

        if (event.getPriority().equals("High")){
            itemView.setBackgroundColor(context.getResources().getColor(R.color.transparent_red));
        } else if (event.getPriority().equals("Medium")){
            itemView.setBackgroundColor(context.getResources().getColor(R.color.transparent_green));
        } else {
            itemView.setBackgroundColor(context.getResources().getColor(R.color.transparent_white));
        }

        ((TextView) itemView.findViewById(R.id.nameTvEventItem)).setText(event.getName());
        ((TextView) itemView.findViewById(R.id.descTvEventItem)).setText(event.getDescription());
        ((TextView) itemView.findViewById(R.id.timeTvEventItem)).setText(event.getTime());
        ((TextView) itemView.findViewById(R.id.urlTvEventItem)).setText(event.getUrl());

    }

}
