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


public class ItemListRecyclerFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_recycler, container, false);

        recyclerView = view.findViewById(R.id.item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        itemViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<ItemListItem>>() {
            @Override
            public void onChanged(List<ItemListItem> items) {
                adapter = new ItemRecyclerViewAdapter(items, new ItemRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ItemListItem item) {
                        itemViewModel.select(item);
                        Navigation.findNavController(view).navigate(R.id.action_itemListRecyclerFragment_to_itemDetailFragment);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        return view;
    }
}