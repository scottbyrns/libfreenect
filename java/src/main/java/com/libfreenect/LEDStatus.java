package com.libfreenect;

/**
 * Created by scott Date: Nov 15, 2010 Time: 8:07:35 PM
 */
public enum LEDStatus {

    OFF(0),
    GREEN(1),
    RED(2),
    YELLOW(3),
    BLINKING_YELLOW(4),
    BLINKING_GREEN(5),
    ALTERNATE_RED_YELLOW(6),
    ALTERNATE_RED_GREEN(7);

    private int status;
    LEDStatus(int status)
    {
        this.status = status;
    }
    public int status() { return status; }
}
