package com.kitalda.cgu.flickrphotos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.kitalda.cgu.flickrphotos.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    private PhotoList images;

    public void add(int position, Photo photo){
        images.add(photo);
        notifyItemInserted(position);
    }

    public void remove(int position){
        images.remove(position);
        notifyItemRemoved(position);
    }
    
    public GalleryAdapter(Context context, PhotoList images){
        this.context = context;
        this.images = images;
    }

    /**
     * Creates new views. Invoked by the layout manager.
     */
    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cell_layout, parent, false);
        return new ViewHolder(v);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Photo image = images.get(position);
        holder.txtTitle.setText(image.getTitle());
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //holder.img.setImageDrawable();
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.image, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return images.size();
    }

    /**
     * Viewholder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private View layout;
        private ImageView img;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtTitle = v.findViewById(R.id.cell_img_title_txt);
            img = v.findViewById(R.id.gallery_cell_img);
        }
    }
}
