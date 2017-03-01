package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by christophertse on 2017/02/23.
 */
public class Model {
    private String exp;
    private String mem;
    private String[] split;
    private ArrayList<String> splitList = new ArrayList<>();
    private ArrayList<Listener> listeners = new ArrayList<>();

    public Model() {
        exp = "0";
        splitString();
    }

    public boolean isZero() {
        if (exp.equals("0")) return true;
        return false;
    }

    public void input(String input) {
        if (exp.equals("0")) {
            System.out.println("Was zero");
            System.out.println(exp);
            exp = input;
        }
        else exp += input;
        splitString();
        notifyNumberListener();
        System.out.println("Current exp: " + exp);
    }

    public double eval() {
        return 0;
    }

    public boolean isValid() {
        for (String str : split) {
            if (str.matches("[-+*^/]{2,}")) return false;
        }
        if(split[0].matches("[-+*^/]+") || split[split.length - 1].matches("[-+*^/]+")) return false;
        return true;
    }

    public void debug() {
        System.out.println(splitList.toString());
        System.out.println(isValid());
    }

    public void clear() {
        exp = "0";
        splitString();
        notifyNumberListener();
    }

    public void memSet(String input) {
        mem = input;
    }

    public void getMem() {
        input(mem);
    }

    private void splitString() {
        splitList.clear();
        split = exp.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        Collections.addAll(splitList, split);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void notifyNumberListener() {
        //System.out.println("Listener called");
        for(Listener listener : listeners ) {
            listener.numberChanged(exp);
        }
    }
}
