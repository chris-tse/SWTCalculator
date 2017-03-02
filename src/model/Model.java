package model;

import java.util.ArrayList;
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

    public void input(String input) {
        if (exp.equals("0")) {
            System.out.println("Was zero");
            System.out.println(exp);
            exp = input;
        }
        else exp += input;
        notifyNumberListener();
        System.out.println("Current exp: " + exp);
    }

    public void del() {
        if (exp != null && exp.length() > 1) {
            exp = exp.substring(0, exp.length()-1);
        } else {
            exp = "0";
        }

        notifyNumberListener();
    }

    public void eval() {
        splitString();
        if(!isValid()) {
            notifyInvalidListener();
        } else if(splitList.size() <= 1) {
            return;
        } else {
            splitString();
            int index;
            double result = 0;
            System.out.println(exp);
            while (splitList.contains("^")) {
                index = splitList.indexOf("^");
                result = Math.pow(Double.parseDouble(splitList.get(index-1)), Double.parseDouble(splitList.get(index+1)));
                splitList.set(index - 1, String.valueOf(result));
                splitList.remove(index);
                splitList.remove(index);
                System.out.println(splitList.toString());
            }

            while (splitList.contains("/")) {
                index = splitList.indexOf("/");
                result = Double.parseDouble(splitList.get(index-1)) / Double.parseDouble(splitList.get(index+1));
                splitList.set(index - 1, String.valueOf(result));
                splitList.remove(index);
                splitList.remove(index);
                System.out.println(splitList.toString());
            }

            while (splitList.contains("*")) {
                index = splitList.indexOf("*");
                result = Double.parseDouble(splitList.get(index-1)) * Double.parseDouble(splitList.get(index+1));
                splitList.set(index - 1, String.valueOf(result));
                splitList.remove(index);
                splitList.remove(index);
                System.out.println(splitList.toString());
            }

            while (splitList.contains("-")) {
                index = splitList.indexOf("-");
                result = Double.parseDouble(splitList.get(index-1)) - Double.parseDouble(splitList.get(index+1));
                splitList.set(index - 1, String.valueOf(result));
                splitList.remove(index);
                splitList.remove(index);
                System.out.println(splitList.toString());
            }

            while (splitList.contains("+")) {
                index = splitList.indexOf("+");
                result = Double.parseDouble(splitList.get(index-1)) + Double.parseDouble(splitList.get(index+1));
                splitList.set(index - 1, String.valueOf(result));
                splitList.remove(index);
                splitList.remove(index);
                System.out.println(splitList.toString());
            }

            exp = String.valueOf(result);
            notifyNumberListener();
        }
    }

    public boolean isValid() {
        for (int i = 0; i < split.length - 2 ; i++)
            if(split[i].matches("[-+*^/]") && split[i+1].matches("[-+*^/]")) return false;

        return true;
    }

    public void debug() {
        splitString();
        System.out.println(splitList.toString());
        System.out.println(isValid());
    }

    public void clear() {
        exp = "0";
        notifyNumberListener();
    }

    public void memSet() {
        mem = exp;
    }

    public void getMem() {
        input(mem);
    }

    private void splitString() {
        splitList.clear();
        split = exp.split("(?<=[-+*/^()])|(?=[-+*/^()])");
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

    public void notifyInvalidListener() {
        //System.out.println("Listener called");
        for(Listener listener : listeners ) {
            listener.invalidCompute();
        }
    }


}
