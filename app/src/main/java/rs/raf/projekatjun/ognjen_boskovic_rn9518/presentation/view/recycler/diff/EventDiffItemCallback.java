package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.recycler.diff;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;

public class EventDiffItemCallback extends DiffUtil.ItemCallback<Event> {
    @Override
    public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.getName().equals(newItem.getName())
                && oldItem.getDescription().equals(newItem.getDescription());
    }
}
