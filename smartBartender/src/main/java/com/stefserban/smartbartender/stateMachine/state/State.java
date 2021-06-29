package com.stefserban.smartbartender.stateMachine.state;

import com.stefserban.smartbartender.stateMachine.context.SmartBartender;

public interface State {
    void nextState(SmartBartender smartBartenderContext);
    void stateAction(SmartBartender smartBartenderContext);
}
