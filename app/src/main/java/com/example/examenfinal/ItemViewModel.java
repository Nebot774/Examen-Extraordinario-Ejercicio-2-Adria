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
    MutableLiveData<Item> selectedItemMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<ItemListItem>> listItemsMutableLiveData = new MutableLiveData<>();
    ItemListItem selected;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        PokeAPI.getItemList(listItemsMutableLiveData);
    }

    MutableLiveData<List<ItemListItem>> getAllItems() {
        return listItemsMutableLiveData;
    }

    public void select(ItemListItem itemListItem) {
        selected = itemListItem;
    }

    public MutableLiveData<Item> getSelectedItem() {
        if (selected != null) {
            int itemId = extractItemIdFromUrl(selected.getUrl());
            PokeAPI.getItem(itemId, selectedItemMutableLiveData);
        }
        return selectedItemMutableLiveData;
    }

    private int extractItemIdFromUrl(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }



}
