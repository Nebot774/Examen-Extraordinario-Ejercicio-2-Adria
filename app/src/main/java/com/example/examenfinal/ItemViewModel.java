package com.example.examenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.Item;
import com.example.examenfinal.models.ItemListItem;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    // LiveData para el item seleccionado
    MutableLiveData<Item> selectedItemMutableLiveData = new MutableLiveData<>();
    // LiveData para la lista de items
    MutableLiveData<List<ItemListItem>> listItemsMutableLiveData = new MutableLiveData<>();
    // Almacena el item seleccionado
    ItemListItem selected;

    // Constructor que inicializa el ViewModel y obtiene la lista de items
    public ItemViewModel(@NonNull Application application) {
        super(application);
        // Cargar la lista de items desde la API
        PokeAPI.getItemList(listItemsMutableLiveData);
    }

    // Devuelve el LiveData que contiene la lista de items
    MutableLiveData<List<ItemListItem>> getAllItems() {
        return listItemsMutableLiveData;
    }

    // Selecciona un item y lo almacena en 'selected'
    public void select(ItemListItem itemListItem) {
        selected = itemListItem;
    }

    // Devuelve el LiveData que contiene el item seleccionado
    public MutableLiveData<Item> getSelectedItem() {
        if (selected != null) {
            // Extrae el ID del item a partir de su URL y carga los datos del item
            int itemId = extractItemIdFromUrl(selected.getUrl());
            PokeAPI.getItem(itemId, selectedItemMutableLiveData);
        }
        return selectedItemMutableLiveData;
    }

    // Extrae el ID del item desde la URL
    private int extractItemIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}

