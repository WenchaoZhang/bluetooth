package com.example.myapplication3.broadcast;

/**
 * Created by admin on 2017/10/28.
 */

public class Parameter {

    //pop列表
    public static final String SCAN = "scan";
    public static final String SETTING = "setting";
    public static final String PAUSE = "pause";
    public static final String ABOUT = "about";
    public static final String EXIT = "exit";

    //cup and holder status
    public static Boolean isCupHolderPress = false;

    //cup and holder status
    public  static Boolean isCupOn = true;
    public static final byte CUP_ON = (byte)'A';
    public static final byte CUP_OFF = (byte)'B';
    public  static Boolean isHolderOn = true;
    public static final byte HOLDER_ON = (byte)'C';
    public static final byte HOLDER_OFF = (byte)'D';

    //key status
    public static final byte UP_KEY_UP = (byte)'a';
    public static final byte UP_KEY_DOWN = (byte)'b';
    public static final byte DOWN_KEY_UP = (byte)'c';
    public static final byte DOWN_KEY_DOWN = (byte)'d';
    public static final byte LEFT_KEY_UP = (byte)'e';
    public static final byte LEFT_KEY_DOWN = (byte)'f';
    public static final byte RIGUT_KEY_UP = (byte)'g';
    public static final byte RIGHT_KEY_DOWN = (byte)'h';
    public static final byte FRONT_KEY_UP = (byte)'i';
    public static final byte FRONT_KEY_DOWN = (byte)'j';
    public static final byte BACK_KEY_UP = (byte)'k';
    public static final byte BACK_KEY_DOWN = (byte)'l';

    //暂停状态
    public static Boolean isPause = false;

    //数据状态
    public static Boolean isGetNewDate = false;
    public static byte newDate = (byte)0;

    public static String pop = "";
}
