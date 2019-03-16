package elixer.com.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import elixer.com.notes.model.Note;

public class NoteAdapter extends RecyclerView.Adapter <NoteAdapter.NoteHolder>{
    private List<Note> notes = new ArrayList<>();


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));

    }


    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class NoteHolder extends RecyclerView.ViewHolder {
        public TextView title, description, priority;

        public NoteHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textView_title);
            description = (TextView) view.findViewById(R.id.textView_desc);
            priority = (TextView) view.findViewById(R.id.textView_priority);
        }
    }
    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();

    }
}
