package com.example.week4day4urbandictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4day4urbandictionary.model.urbandictionary.ListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class UrbanDictAdapter extends RecyclerView.Adapter<UrbanDictAdapter.ViewHolder> {
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    List<ListItem> defList;

    public UrbanDictAdapter(List<ListItem> defList) {
        this.defList = defList;
    }

    public void setDefList(List<ListItem> defList) {
        this.defList = defList;
        notifyDataSetChanged();
    }

    public void sort(boolean upvotes){
        if(upvotes) {
            Collections.sort(defList, new Comparator<ListItem>() {
                @Override
                public int compare(ListItem one, ListItem two) {
                    return one.getThumbsUp() > two.getThumbsUp() ? -1 : one.getThumbsUp() < two.getThumbsUp() ? 1 : 0;
                }
            });
        } else { //sort by downvotes
            Collections.sort(defList, new Comparator<ListItem>() {
                @Override
                public int compare(ListItem one, ListItem two) {
                    return one.getThumbsDown() > two.getThumbsDown() ? -1 : one.getThumbsDown() < two.getThumbsDown() ? 1 : 0;
                }
            });
        }
        notifyDataSetChanged();
    }

    private String parseDate(String passedDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(passedDate);
            sdf.applyPattern("MMMM, dd yyyy");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return passedDate;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dict_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem currentItem = defList.get(position);
        holder.tvSearchedWord.setText(currentItem.getWord());
        holder.tvDefinition.setText(currentItem.getDefinition().replaceAll("[\\[\\]]", ""));
        holder.tvExamples.setText(currentItem.getExample().replaceAll("[\\[\\]]", ""));
        holder.tvUpCount.setText(String.valueOf(currentItem.getThumbsUp()));
        holder.tvDownCount.setText(String.valueOf(currentItem.getThumbsDown()));
        holder.tvAuthorDate.setText(String.format("By %s\nOn %s", currentItem.getAuthor(), parseDate(currentItem.getWrittenOn().substring(0,10))));

    }

    @Override
    public int getItemCount() {
        return defList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSearchedWord, tvDefinition, tvExamples, tvUpCount, tvDownCount, tvAuthorDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchedWord = itemView.findViewById(R.id.tvSearchedWord);
            tvDefinition = itemView.findViewById(R.id.tvDefinition);
            tvExamples = itemView.findViewById(R.id.tvExamples);
            tvUpCount = itemView.findViewById(R.id.tvUpCount);
            tvDownCount = itemView.findViewById(R.id.tvDownCount);
            tvAuthorDate = itemView.findViewById(R.id.tvAuthorDate);
        }
    }
}
