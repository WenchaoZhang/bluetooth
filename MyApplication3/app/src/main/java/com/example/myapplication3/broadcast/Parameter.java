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

    //设置步长
    public  static Boolean isStepChange = false;
    public static byte STEP = (byte)'E';

    public static final byte STEP1 = (byte)'E';
    public static final byte STEP2 = (byte)'F';
    public static final byte STEP3 = (byte)'G';
    public static final byte STEP4 = (byte)'H';

    //设置slider的舵机
    public static final String SLIDER0 = "I";
    public static final String SLIDER1 = "J";
    public static final String SLIDER2 = "K";
    public static final String SLIDER3 = "L";
    public static final String SLIDER4 = "M";
    public static final String SLIDER5 = "N";
    public static final String SLIDER6 = "0";


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
    public static final byte THREEL_KEY_UP = (byte)'m';
    public static final byte THREEL_KEY_DOWN = (byte)'n';
    public static final byte THREER_KEY_UP = (byte)'o';
    public static final byte THREER_KEY_DOWN = (byte)'p';
    public static final byte FOURL_KEY_UP = (byte)'q';
    public static final byte FOURL_KEY_DOWN = (byte)'r';
    public static final byte FOURR_KEY_UP = (byte)'s';
    public static final byte FOURR_KEY_DOWN = (byte)'t';
    public static final byte FIVEL_KEY_UP = (byte)'u';
    public static final byte FIVEL_KEY_DOWN = (byte)'v';
    public static final byte FIVER_KEY_UP = (byte)'w';
    public static final byte FIVER_KEY_DOWN = (byte)'x';

    //滑块数据及判断
    public static Boolean isSliderChange = false;
    public static String whichSlider = "0";
    public static int sliderValue = 0;

    //暂停状态
    public static Boolean isPause = false;

    //数据状态
    public static Boolean isGetNewDate = false;
    public static byte newDate = (byte)0;

    public static String pop = "";
}
