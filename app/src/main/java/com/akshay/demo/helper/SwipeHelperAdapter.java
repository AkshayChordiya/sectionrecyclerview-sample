package com.akshay.demo.helper;

import android.support.v7.widget.RecyclerView;

public abstract class SwipeHelperAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public abstract void add(T t, int position);

    public abstract void remove(int position);
}
