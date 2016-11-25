package com.akshay.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;

/**
 * Created by chordiya_ak on 11/25/2016.
 */

public class LoremAdapter extends SectionedRecyclerViewAdapter<LoremAdapter.LoremHolder> {

    private Context mContext;
    private AdapterView.OnItemClickListener onItemClickListener;

    public LoremAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public LoremHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LoremHolder(LayoutInflater.from(mContext)
                .inflate(viewType == VIEW_TYPE_ITEM ? R.layout.row_layout : R.layout.row_header, null));
    }

    @Override
    public int getSectionCount() {
        return 20;
    }

    @Override
    public int getItemCount(int section) {
        if (section % 2 == 0)
            return 4; // even sections get 4 items
        return 1; // odd get 8
    }

    @Override
    public void onBindHeaderViewHolder(LoremHolder holder, int section) {
        holder.title.setText(String.format("Section %d", section));
    }

    @Override
    public void onBindViewHolder(LoremHolder holder, int section, int relativePosition, final int absolutePosition) {
        holder.title.setText("Brunch this weekend?");
        if (holder.message != null) {
            holder.message.setText("I'll be in your near by doing errands. Do you want to grab");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, view, absolutePosition, 0);
            }
        });
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class LoremHolder extends RecyclerView.ViewHolder{

        public final TextView title;
        public final TextView message;

        public LoremHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            message = (TextView) itemView.findViewById(R.id.message);
        }

    }
}
