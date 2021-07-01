package com.stefserban.smartbartender.stateMachine.state;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.stefserban.smartbartender.stateMachine.context.SmartBartender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CleaningState implements State {
    private Logger LOGGER = LoggerFactory.getLogger(CleaningState.class);

    @Override
    public void nextState(SmartBartender smartBartenderContext) {
        smartBartenderContext.setState(ReadyState.getInstance());
    }

    @Override
    public void stateAction(SmartBartender smartBartenderContext) {
        LOGGER.info("Cleaning state in action!");

        Map<String, DigitalOutput> pumpMap = smartBartenderContext.getRaspberryPiConfiguration().getIngredientPumpMap();
        for (String key: pumpMap.keySet()) {
            DigitalOutput pump = pumpMap.get(key);
            if (pump.equals(DigitalState.HIGH)) {
                LOGGER.info("Pump is already running!");
            } else {
                LOGGER.info("Starting cleaning pump for {}!", key);
                pump.high();
            }
            this.waitForPumpToRun();
            LOGGER.info("Pump finished!");
            pump.low();
        }
        this.nextState(smartBartenderContext);
    }

    private void waitForPumpToRun() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
