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
        Map<String, Integer> pumps = smartBartenderContext.getRaspberryPiConfiguration().getIngredientToPinMap();
        for (String key : pumps.keySet()) {
            LOGGER.info("Cleaning pump with pin {}", pumps.get(key));

            //TODO - to be moved in the config class?
            Context pi4j = smartBartenderContext.getRaspberryPiConfiguration().getPi4j();
            DigitalOutputConfigBuilder pumpConfig = DigitalOutput.newConfigBuilder(pi4j)
                    .id("led")
                    .name("LED Flasher")
                    .address(pumps.get(key))
                    .shutdown(DigitalState.LOW)
                    .initial(DigitalState.LOW)
                    .provider("pigpio-digital-output");
            DigitalOutput led = pi4j.create(pumpConfig);
            if (led.equals(DigitalState.HIGH)) {
                LOGGER.info("PUMP is already running");
            } else {
                LOGGER.info("Turning on the pump for 2 seconds");
                led.high();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("Turning off the pump!");
                led.low();
            }
        }
        this.nextState(smartBartenderContext);
    }
}
