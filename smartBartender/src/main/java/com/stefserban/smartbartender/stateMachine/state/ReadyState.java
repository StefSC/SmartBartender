package com.stefserban.smartbartender.stateMachine.state;

import com.stefserban.smartbartender.stateMachine.context.SmartBartender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyState implements State {
    private static Logger LOGGER = LoggerFactory.getLogger(ReadyState.class);
    private static final ReadyState INSTANCE = new ReadyState();
    private State nextState = null;

    private ReadyState() {

    }

    public static ReadyState getInstance() {
        return INSTANCE;
    }

    @Override
    public void nextState(SmartBartender smartBartenderContext) {
        LOGGER.info("Going into a new state!");
        smartBartenderContext.setState(nextState);
    }

    @Override
    public void stateAction(SmartBartender smartBartenderContext) {
        LOGGER.info("Ready for use! Waiting for user input");
        if (this.nextState == null) {
            this.nextState = new CleaningState();
        } else {
            this.nextState = new ShutdownState();
        }
        this.nextState(smartBartenderContext);

    }
}
