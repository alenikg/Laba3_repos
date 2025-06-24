
package com.mycompany.laba3_monst;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;
/**
 *
 * @author elenagoncarova
 */
public class Monster {
    private String dataSource;
    private String name;
    private String description;
    private int danger;
    private String habitat;
    private Date firstMention;
    private String vulnerability;
    private String invulnerability;
    private String height;
    private String weight;
    private List<String> immunities;
    private String activeTime;
    public Recipe recipe;
    
    public String getDataSource() {
        return dataSource;
    }

    @JsonIgnore
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    
        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDanger() {
        return danger;
    }

    public void setDanger(int danger) {
        this.danger = danger;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    public Date getFirstMention() {
        return firstMention;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setFirstMention(Date firstMention) {
        this.firstMention = firstMention;
    }
    
    public String getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }

    public String getInvulnerability() {
        return invulnerability;
    }

    public void setInvulnerability(String invulnerability) {
        this.invulnerability = invulnerability;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<String> getImmunities() {
        return immunities;
    }

    public void setImmunities(List<String> immunities) {
        this.immunities = immunities;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @JsonIgnore
    public String getObjPropsDesc() {
        return "Чудовище {" +
                "имя ='" + name + '\'' +
                ", источник данных ='" + dataSource + '\'' +
                ", описание ='" + description + '\'' +
                ", уровень опасности =" + danger +
                ", среда обитания ='" + habitat + '\'' +
                ", дата первого упоминания =" + firstMention +
                ", уязвимость ='" + vulnerability + '\'' +
                ", неуязвимость ='" + invulnerability + '\'' +
                ", рост ='" + height + '\'' +
                ", вес ='" + weight + '\'' +
                ", иммунитет =" + immunities +
                ", время активности ='" + activeTime + '\'' +
                ", рецепт =" + recipe +
                '}';
    }

    @Override
    public String toString() {
        return name;
    }
    
}
