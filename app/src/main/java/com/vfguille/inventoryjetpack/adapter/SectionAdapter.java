package com.vfguille.inventoryjetpack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.data.repository.SectionRepository;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    private ArrayList<Section> list;
    private OnManageSectionListener onManageSectionListener;
    private Context context;

    public interface OnManageSectionListener{
        void onEditSection(Section section);
        void onDeleteSection(Section section);
    }

    public SectionAdapter(){
        list = new ArrayList<>();
    }

    // Los datos se obtienen desde el repository.
    public SectionAdapter(OnManageSectionListener listener, Context context){
        onManageSectionListener = listener;
        this.context = context;
    }
    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RequestOptions options = new RequestOptions()
                .centerCrop();
        holder.tvName.setText(list.get(position).getName());
        Glide.with(holder.itemView.getContext()).load("https://i.kym-cdn.com/photos/images/newsfeed/001/003/188/daa.jpg").apply(options).into(holder.icon);
        holder.bind(list.get(position), onManageSectionListener);
    }

    public void setOnManageSectionClickListener(OnManageSectionListener onManageSectionClickListener){
        this.onManageSectionListener = onManageSectionClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear(){
        list.clear();
    }

    public void load(List<Section> sectionList) {
        list.addAll(sectionList);
        notifyDataSetChanged();
    }

    public void delete(Section deleted) {
        list.remove(deleted);
    }

    public void add(Section undoDeleted) {
        list.add(undoDeleted);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.ivIconSection);
            tvName = itemView.findViewById(R.id.tvName);
        }

        void bind(final Section section, final OnManageSectionListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditSection(section);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteSection(section);
                    return true;
                }
            });
        }
    }
}
