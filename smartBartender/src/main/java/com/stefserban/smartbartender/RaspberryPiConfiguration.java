package com.stefserban.smartbartender;

import com.pi4j.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RaspberryPiConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(RaspberryPiConfiguration.class);
    private Context pi4j;
    private Map<String, Integer> ingredientToPinMap;

    public Context getPi4j() {
        return pi4j;
    }

    public void setPi4j(Context pi4j) {
        this.pi4j = pi4j;
    }

    public Map<String, Integer> getIngredientToPinMap() {
        return ingredientToPinMap;
    }

    public void setIngredientToPinMap(Map<String, Integer> ingredientToPinMap) {
        this.ingredientToPinMap = ingredientToPinMap;
    }
}
