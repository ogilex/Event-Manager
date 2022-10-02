package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.recycler.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.function.Function;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder.EventViewHolder;

public class EventAdapter extends ListAdapter<Event, EventViewHolder> {

    private final Function<Event, Void> onDeleteClicked;
    private final Function<Event, Void> onItemClicked;

    public EventAdapter(@NonNull DiffUtil.ItemCallback<Event> diffCallback, Function<Event, Void> onDeleteClicked, Function<Event, Void> onItemClicked) {
        super(diffCallback);
        this.onDeleteClicked = onDeleteClicked;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view, parent.getContext(),
                position -> {
                    Event event = getItem(position);
                    onDeleteClicked.apply(event);
                    return null;
                },
                position -> {
                    Event event = getItem(position);
                    onItemClicked.apply(event);
                    return null;
                });
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        Event event = getItem(position);

        holder.bind(event);
    }
}
