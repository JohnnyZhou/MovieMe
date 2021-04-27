package com.johnnyzhou.movieme.ui.person.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.johnnyzhou.movieme.R;
import com.johnnyzhou.movieme.ui.person.Person;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {
    private List<Person> people;
    private Context context;

    @Inject
    public PersonListAdapter(Context context, List<Person> people) {
        this.context = context;
        this.people = people;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = people.get(position);

        Glide.with(context)
                .load(person.getImageUrl())
                .into(holder.profileImageView);

        holder.nameTextView.setText(person.getName());
        holder.knownForTextView.setText(person.getKnownFor());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.nameTextView)
        TextView nameTextView;
        @Bind(R.id.profileImageView)
        ImageView profileImageView;
        @Bind(R.id.knownForTextView)
        TextView knownForTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new PersonListClickEvent(getAdapterPosition()));
        }
    }
}
