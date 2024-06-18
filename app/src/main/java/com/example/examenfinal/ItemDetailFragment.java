package com.example.examenfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenfinal.models.Item;

import java.util.List;


//clase que representa el detalle de un item
public class ItemDetailFragment extends Fragment {
    private ItemViewModel itemViewModel;
    private TextView itemName;
    private TextView itemCategory;
    private TextView itemCost;
    private TextView itemEffects;
    private ImageView itemImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        //referenciamos los elementos del layout
        itemName = view.findViewById(R.id.item_name);
        itemCategory = view.findViewById(R.id.item_category);
        itemCost = view.findViewById(R.id.item_cost);
        itemEffects = view.findViewById(R.id.item_effects);
        itemImage = view.findViewById(R.id.item_image);

        //instancia del view model
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        //se llama al metodo para observar el item seleccionado
        observeSelectedItem();

        return view;
    }

    //metodo para observar el item seleccionado y aplicarle los valores a los elementod de el layout
    private void observeSelectedItem() {
        itemViewModel.getSelectedItem().observe(getViewLifecycleOwner(), new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                if (item != null) {
                    itemName.setText(item.getName());
                    itemCategory.setText(item.getCategory().getName());
                    itemCost.setText(String.valueOf(item.getCost()));
                    itemEffects.setText(getEffectsString(item.getEffectEntries()));

                    String imageUrl = item.getImageUrl();
                    Log.d("ItemDetailFragment", "Image URL: " + imageUrl); //Log para verificar la URL

                    // Cargar la imagen usando  Glide
                    Glide.with(getContext())
                            .load(item.getImageUrl())
                            .into(itemImage);


                }
            }
        });
    }

    //metodo para recorrer y asignarle los efectos a el item
    private String getEffectsString(List<Item.EffectEntry> effectEntries) {
        StringBuilder effects = new StringBuilder();
        for (Item.EffectEntry entry : effectEntries) {
            effects.append(entry.getShortEffect()).append("\n\n");
        }
        return effects.toString().trim();
    }
}