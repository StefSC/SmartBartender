package com.stefserban.smartbartender;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RaspberryPiConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(RaspberryPiConfiguration.class);
    private static Context PI4J = Pi4J.newAutoContext();
    private Map<String, DigitalOutput> ingredientPumpMap;

    public Context getPi4j() {
        return PI4J;
    }

    public void setPi4j(Context pi4j) {
        this.PI4J = pi4j;
    }

    public Map<String, DigitalOutput> getIngredientPumpMap() {
        return ingredientPumpMap;
    }

    public void shutdown() {
        LOGGER.info("SHUTDOWN was called! Hope you enjoyed your drinks! Cheers!");
        if (this.PI4J != null) {
            this.PI4J.shutdown();
        }
    }

    public void addPump(String alcohol, Integer pin) {
        LOGGER.info("Adding {} at pin {}", alcohol, pin);
        if (this.ingredientPumpMap == null) {
            this.ingredientPumpMap = new HashMap<>();
        }
        DigitalOutputConfigBuilder pumpConfig = DigitalOutput.newConfigBuilder(PI4J)
                .id(alcohol)
                .name(alcohol)
                .address(pin)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");
        DigitalOutput pump = PI4J.create(pumpConfig);
        this.ingredientPumpMap.put(alcohol, pump);
    }
}
