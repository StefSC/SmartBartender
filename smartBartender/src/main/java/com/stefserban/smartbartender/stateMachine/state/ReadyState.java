package com.stefserban.smartbartender.stateMachine.state;

import com.pi4j.io.gpio.digital.DigitalInput;
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

    /**
     * make it wait for user input somehow?
     * should get the desired cocktail from the user and send it to the preparing state
     * @param smartBartenderContext
     */
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

//    var buttonConfig = DigitalInput.newConfigBuilder(pi4j)
//            .id("button")
//            .name("Press button")
//            .address(PIN_BUTTON)
//            .pull(PullResistance.PULL_DOWN)
//            .debounce(3000L)
//            .provider("pigpio-digital-input");
//    var button = pi4j.create(buttonConfig);
//        button.addListener(e -> {
//        if (e.state() == DigitalState.LOW) {
//            pressCount++;
//            console.println("Button was pressed for the " + pressCount + "th time");
//        }
//    });


}
