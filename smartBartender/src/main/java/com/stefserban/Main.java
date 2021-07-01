package com.stefserban;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;
import com.stefserban.smartbartender.stateMachine.context.SmartBartender;
import com.stefserban.smartbartender.stateMachine.state.ShutdownState;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Stef Serban-Cristian
 */
public class Main {

    private static final int PIN_LED = 27; // PIN 15 = BCM 22
    private static final Console console = new Console();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        SmartBartender smartBartender = new SmartBartender();
        while (smartBartender.getState().getClass() != ShutdownState.class) {
            smartBartender.doStateAction();
        }
        smartBartender.shutdown();

//        console.box("Hello Rasbian world !");
//        Context pi4j = null;
//        try {
//            pi4j = Pi4J.newAutoContext();
//            new Main().run(pi4j);
//        } catch (InvocationTargetException e) {
//            console.println("Error: " + e.getTargetException().getMessage());
//        } catch (Exception e) {
//            console.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (pi4j != null) {
//                pi4j.shutdown();
//            }
//        }
    }

//    private void run(Context pi4j) throws Exception {
//        Platforms platforms = pi4j.platforms();
//
//        console.box("Pi4J PLATFORMS");
//        console.println();
//        platforms.describe().print(System.out);
//        console.println();
//
//        var ledConfig = DigitalOutput.newConfigBuilder(pi4j)
//                        .id("led")
//                        .name("LED Flasher")
//                        .address(PIN_LED)
//                        .shutdown(DigitalState.LOW)
//                        .initial(DigitalState.LOW)
//                        .provider("pigpio-digital-output");
//
//        var led = pi4j.create(ledConfig);
//        int counter = 0;
//        while (counter < 50) {
//            if (led.equals(DigitalState.HIGH)) {
//                led.low();
//                System.out.println("low");
//            } else {
//                led.high();
//                System.out.println("high");
//            }
//            Thread.sleep(500);
//            counter++;
//        }
//    }

}
