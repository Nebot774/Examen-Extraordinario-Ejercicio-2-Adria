package com.example.examenfinal.models;

//clase que representa el elemento item que se mostrara el nombre el el recycler de items
public class ItemListItem {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    // Método para obtener la URL de la imagen
    public String getImageUrl() {
        // El nombre del ítem en la URL de la imagen suele estar en minúsculas
        String itemName = name.toLowerCase().replace(" ", "-");
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/" + itemName + ".png";
    }





}
