package com.example.project2;

import java.util.*;
public class Test extends Question {
    public int numOfOptions = 4;
    public  String options[] = new String[numOfOptions];
    public ArrayList<String> labels;

    public Test() {
    }

    public String[] getOptions(String [] options)
    {
        return options;
    }

    public void setOptions(String[] options) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < numOfOptions; i++) {
            list.add(options[i]);
        }
        Collections.shuffle(list);
        for (int i = 0; i < numOfOptions; i++) {
            this.options[i] = list.get(i);
        }
    }

    public String getOptionAt(int ind) {
        return options[ind];
    }

    public String toString() {
        String k = getDescription() + "\n";
        for (int i = 0; i < numOfOptions; i++) {
            k=k+ (getOptionAt(i) + "\n");
        }
        return k;
    }
}
