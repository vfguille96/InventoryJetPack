package com.vfguille.inventoryjetpack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.data.repository.DependencyRepository;

import java.util.ArrayList;
import java.util.List;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {
    private ArrayList<Dependency> list;
    private OnManageDependencyListener onManageDependencyListener;

    public interface OnManageDependencyListener{
        void onEditDependency(Dependency dependency);
        void onDeleteDependency(Dependency dependency);
    }

    public DependencyAdapter(){
        list = new ArrayList<>();
    }

    // Los datos se obtienen desde el repository.
    public DependencyAdapter(OnManageDependencyListener listener){
        list = DependencyRepository.getInstance().getList();
        onManageDependencyListener = listener;
    }

    @NonNull
    @Override
    public DependencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {
        holder.icon.setLetter(list.get(position).getName());
        holder.tvName.setText(list.get(position).getName());
        holder.bind(list.get(position), onManageDependencyListener);
    }

    public void setOnManageDependencyClickListener(OnManageDependencyListener onManageDependencyClickListener){
        this.onManageDependencyListener = onManageDependencyClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void load(List<Dependency> dependencyList) {
        list.addAll(dependencyList);
        notifyDataSetChanged();
    }

    public void delete(Dependency deleted) {
        list.remove(deleted);
    }

    public void add(Dependency undoDeleted) {
        list.add(undoDeleted);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        MaterialLetterIcon icon;
        TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.ivIconSection);
            tvName = itemView.findViewById(R.id.tvName);
        }

        void bind(final Dependency dependency, final OnManageDependencyListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditDependency(dependency);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteDependency(dependency);
                    return true;
                }
            });
        }
    }
}