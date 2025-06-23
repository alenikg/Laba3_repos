package com.mycompany.laba3_monst;

/**
 *
 * @author elenagoncarova
 */
public class Recipe {
    private String type;
    private String ingredients;
    private int time;
    private String effectiveness;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }

    @Override
    public String toString() {
        return "Рецепт чудовища {" +
                "тип ='" + type + '\'' +
                ", ингредиенты ='" + ingredients + '\'' +
                ", время =" + time +
                ", эффективность ='" + effectiveness + '\'' +
                '}';
    }
}

