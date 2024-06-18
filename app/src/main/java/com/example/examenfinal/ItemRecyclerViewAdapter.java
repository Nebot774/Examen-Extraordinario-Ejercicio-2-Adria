package com.example.examenfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examenfinal.models.ItemListItem;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final List<ItemListItem> items;
    private final OnItemClickListener listener;

    // Interfaz para manejar los clics en los items
    public interface OnItemClickListener {
        void onItemClick(ItemListItem item);
    }

    // Constructor del adaptador
    public ItemRecyclerViewAdapter(List<ItemListItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    // Crea nuevas vistas (invocado por el layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    // Reemplaza el contenido de una vista (invocado por el layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    // Retorna el tamaño de tu dataset (invocado por el layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Proporciona una referencia a las vistas para cada item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView itemName;
        private ImageView itemImage;

        public ViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.item_name);
            itemImage = view.findViewById(R.id.item_image);
        }

        // Vincula los datos del item a las vistas
        public void bind(final ItemListItem item, final OnItemClickListener listener) {
            itemName.setText(item.getName());

            // Cargar la imagen usando Glide
            Glide.with(itemImage.getContext())
                    .load(item.getImageUrl())
                    .into(itemImage);

            // Configurar el listener para el clic en el item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

