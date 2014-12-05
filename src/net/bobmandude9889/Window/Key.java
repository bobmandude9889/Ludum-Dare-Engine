package net.bobmandude9889.Window;

import java.util.ArrayList;
import java.util.List;

public class Key {

	final static int BACK_SPACE = 8;
	final static int ENTER = 10;
	final static int SHIFT = 16;
	final static int CONTROL = 17;
	final static int ALT = 18;
	final static int PAUSE_BREAK = 19;
	final static int CAPS_LOCK = 20;
	final static int SPACE = 32;
	final static int PAGE_UP = 33;
	final static int PAGE_DOWN = 34;
	final static int END = 35;
	final static int HOME = 36;
	final static int LEFT = 37;
	final static int UP = 38;
	final static int RIGHT = 39;
	final static int DOWN = 40;
	final static int DASH = 45;
	final static int ZERO = 48;
	final static int ONE = 49;
	final static int TWO = 50;
	final static int THREE = 51;
	final static int FOUR = 52;
	final static int FIVE = 53;
	final static int SIX = 54;
	final static int SEVEN = 55;
	final static int EIGHT = 56;
	final static int NINE = 57;
	final static int EQUALS = 61;
	final static int A = 65;
	final static int B = 66;
	final static int C = 67;
	final static int D = 68;
	final static int E = 69;
	final static int F = 70;
	final static int G = 71;
	final static int H = 72;
	final static int I = 73;
	final static int J = 74;
	final static int K = 75;
	final static int L = 76;
	final static int M = 77;
	final static int N = 78;
	final static int O = 79;
	final static int P = 80;
	final static int Q = 81;
	final static int R = 82;
	final static int S = 83;
	final static int T = 84;
	final static int U = 85;
	final static int V = 86;
	final static int W = 87;
	final static int X = 88;
	final static int Y = 89;
	final static int Z = 90;
	final static int OPEN_BRACKET = 91;
	final static int BACKSLASH = 92;
	final static int CLOSE_BRACKET = 93;
	final static int NUM_ZERO = 96;
	final static int NUM_ONE = 97;
	final static int NUM_TWO = 98;
	final static int NUM_THREE = 99;
	final static int NUM_FOUR = 100;
	final static int NUM_FIVE = 101;
	final static int NUM_SIX = 102;
	final static int NUM_SEVEN = 103;
	final static int NUM_EIGHT = 104;
	final static int NUM_NINE = 105;
	final static int ASTERISK = 106;
	final static int PLUS = 107;
	final static int MINUS = 109;
	final static int NUM_PERIOD = 110;
	final static int NUM_SLASH = 111;
	final static int F1 = 112;
	final static int F2 = 113;
	final static int F3 = 114;
	final static int F4 = 115;
	final static int F5 = 116;
	final static int F6 = 117;
	final static int F7 = 118;
	final static int F8 = 119;
	final static int F9 = 120;
	final static int F10 = 121;
	final static int F11 = 122;
	final static int F12 = 123;
	final static int DELETE = 127;
	final static int NUMBER_LOCK = 144;
	final static int SCROLL_LOCK = 145;
	final static int INSERT = 155;
	final static int GRAVE = 192;
	
	protected static List<Integer> pressed = new ArrayList<Integer>();
	
	public static boolean isPressed(int key){
		return pressed.contains((Integer) key);
	}
	
	protected static void add(int key){
		pressed.add(key);
	}
	
	protected static void remove(int key){
		pressed.remove(pressed.indexOf(key));
	}
	
}
