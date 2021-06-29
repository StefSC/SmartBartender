package com.stefserban.smartbartender.stateMachine.context;

import com.stefserban.smartbartender.RaspberryPiConfiguration;
import com.stefserban.smartbartender.stateMachine.state.SetupState;
import com.stefserban.smartbartender.stateMachine.state.State;

public class SmartBartender {
    private RaspberryPiConfiguration raspberryPiConfiguration;
    private State state = new SetupState();

    public RaspberryPiConfiguration getRaspberryPiConfiguration() {
        return raspberryPiConfiguration;
    }

    public void setRaspberryPiConfiguration(RaspberryPiConfiguration raspberryPiConfiguration) {
        this.raspberryPiConfiguration = raspberryPiConfiguration;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doStateAction() {
        this.state.stateAction(this);
    }
}
