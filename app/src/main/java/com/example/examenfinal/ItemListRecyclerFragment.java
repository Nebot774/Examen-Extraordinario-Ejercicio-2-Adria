package com.example.examenfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfinal.models.ItemListItem;

import java.util.List;

//clase 
public class ItemListRecyclerFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_item_list_recycler, container, false);

        // Inicializa el RecyclerView y configura su layout manager
        recyclerView = view.findViewById(R.id.item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtiene el ViewModel asociado a la actividad
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        // Observa los cambios en la lista de items y actualiza el adaptador
        itemViewModel.getAllItems().observe(getViewLifecycleOwner(), items -> {
            adapter = new ItemRecyclerViewAdapter(items, item -> {
                // Selecciona el item y navega al detalle del item
                itemViewModel.select(item);
                Navigation.findNavController(view).navigate(R.id.action_itemListRecyclerFragment_to_itemDetailFragment);
            });
            recyclerView.setAdapter(adapter);
        });

        return view;
    }
}
