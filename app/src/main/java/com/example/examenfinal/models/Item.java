package com.example.examenfinal.models;

import java.util.List;

public class Item {
    private String name;
    private Category category;
    private int cost;
    private List<EffectEntry> effect_entries;
    private Sprites sprites;

    public static class Category {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class EffectEntry {
        private String short_effect;

        public String getShortEffect() {
            return short_effect;
        }
    }

    public static class Sprites {
        private String defaultSprite; // Cambiado el nombre de 'default' a 'defaultSprite'

        public String getDefaultSprite() {
            return defaultSprite;
        }
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getCost() {
        return cost;
    }

    public List<EffectEntry> getEffectEntries() {
        return effect_entries;
    }

    public Sprites getSprites() {
        return sprites;
    }

    // Método para obtener la URL de la imagen
    public String getImageUrl() {
        // El nombre del ítem en la URL de la imagen suele estar en minúsculas
        String itemName = name.toLowerCase().replace(" ", "-");
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/" + itemName + ".png";
    }



}
