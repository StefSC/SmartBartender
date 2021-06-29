package com.stefserban.smartbartender.stateMachine.state;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.stefserban.smartbartender.RaspberryPiConfiguration;
import com.stefserban.smartbartender.stateMachine.context.SmartBartender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class SetupState implements State {
    private static Logger LOGGER = LoggerFactory.getLogger(SetupState.class);
    @Override
    public void nextState(SmartBartender smartBartenderContext) {
        LOGGER.debug("Setup complete!");
        smartBartenderContext.setState(ReadyState.getInstance());
    }

    @Override
    public void stateAction(SmartBartender smartBartenderContext) {
        LOGGER.info("Setting up the raspberry pi configuration");
        //maybe read the config from a file here and set it in the context
        Context pi4j = Pi4J.newAutoContext();
        HashMap<String, Integer> pinout = new HashMap<String, Integer>();
        pinout.put("TEQUILA", 27);
//        pinout.put("VODKA", 2);
//        pinout.put("ORANGE JUICE", 3);
//        pinout.put("SUGAR WATER", 4);

        RaspberryPiConfiguration raspberryPiConfiguration = new RaspberryPiConfiguration();
        raspberryPiConfiguration.setPi4j(pi4j);
        raspberryPiConfiguration.setIngredientToPinMap(pinout);

        smartBartenderContext.setRaspberryPiConfiguration(raspberryPiConfiguration);

        this.nextState(smartBartenderContext);
    }
}
