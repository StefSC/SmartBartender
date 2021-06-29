package com.stefserban.smartbartender.stateMachine.state;

import com.stefserban.smartbartender.stateMachine.context.SmartBartender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownState implements State {
    private static Logger LOGGER = LoggerFactory.getLogger(ShutdownState.class);

    @Override
    public void nextState(SmartBartender smartBartenderContext) {

    }

    @Override
    public void stateAction(SmartBartender smartBartenderContext) {
        LOGGER.info("Shutting down!");
    }
}
