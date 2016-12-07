package com.akshay.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.akshay.demo.helper.ItemTouchHelperAdapter;

import java.util.List;

public class LoremAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    private static final String TAG = "LoremAdapter";

    private Context mContext;
    private final SparseArray<List<Message>> mMessages;
    private AdapterView.OnItemClickListener onItemClickListener;

    public LoremAdapter(Context mContext, SparseArray<List<Message>> messages) {
        this.mContext = mContext;
        this.mMessages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.row_header, null));
        } else {
            return new LoremHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.row_layout, null));
        }
    }

    @Override
    public int getSectionCount() {
        return 4;
    }

    @Override
    public int getItemCount(int section) {
        return mMessages.get(section) != null ? mMessages.get(section).size() : 0;
    }

    /*public int[] getPositions() {
        int[] pos = new int[4];
        for (int i = 0; i < mMessages.size(); i++) {
            Message message = mMessages.get(i);
            switch (message.getSectionType()) {
                case Message.TODAY:
                    pos[0]++;
                    break;
                case Message.YESTERDAY:
                    pos[1]++;
                    break;
                case Message.MONTH:
                    pos[2]++;
                    break;
                case Message.OLDER:
                    pos[3]++;
                    break;
            }
        }
        return pos;
    }*/

    public void remove(int position) {
        if (position < getItemCount()) {
            mMessages.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.title.setText(mContext.getResources().getStringArray(R.array.timeHeader)[section]);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder loremHolder, int section, int relativePosition, final int absolutePosition) {
        if (loremHolder instanceof LoremHolder) {
            LoremHolder holder = (LoremHolder) loremHolder;
            Log.d(TAG, "onBindViewHolder: Sec = " + section);
            Log.d(TAG, "onBindViewHolder: Rel = " + relativePosition);
            Log.d(TAG, "onBindViewHolder: Pos = " + absolutePosition);
            Message message = mMessages.get(section).get(relativePosition);
            holder.title.setText(message.getMessage());
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
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        int[] sectionAndPos = getSectionIndexAndRelativePosition(position);
        List<Message> messages = mMessages.get(sectionAndPos[0]);
        Log.d(TAG, "onItemDismiss: Size = " + messages.size());
        if (messages.size() == 1) {
            Log.d(TAG, "onItemDismiss: Remove = " + sectionAndPos[0]);
            mMessages.remove(sectionAndPos[0]);
        } else {
            messages.remove(sectionAndPos[1]);
            mMessages.put(sectionAndPos[0], messages);
        }
        notifyDataSetChanged();
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        public final TextView title;

        HeaderHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }

    }

    public class LoremHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final TextView message;

        LoremHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            message = (TextView) itemView.findViewById(R.id.message);
        }

    }
}
