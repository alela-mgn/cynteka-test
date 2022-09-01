package com.cynteka;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/input.txt");
        ArrayList<String> properties = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                properties.add(reader.readLine());
            }

            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                values.add(reader.readLine());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ArrayList<String> result = new ArrayList<>();
        for (String property : properties) {
            String[] splitProperties = property.split(" ");

            String temp = "";
            for (String value : values) {
                String[] splitValue = value.split(" ");

                for (String sProp: splitProperties) {
                    for (String sValue : splitValue) {
                        if (sProp.length() > 3 && sValue.length() > 3 &&
                                (sProp.toLowerCase().contains(sValue.toLowerCase())
                                        || sValue.toLowerCase().contains(sProp.toLowerCase())) ) {
                            temp = property + ":" + value;
                            break;
                        }
                    }
                }
            }
            if (temp.equals("")) {
                temp = property + ":?";
            }
            result.add(temp);
        }

        ArrayList<String> addition = new ArrayList<>();
        for (String res : result) {
            String flagStr = "";
            for (String value: values) {
                if (res.contains(value)) {
                    flagStr = "";
                    break;
                }
                flagStr = value;
            }
            if (!flagStr.equals("") && !addition.contains(flagStr)) {
                addition.add(flagStr + ":?");
            }
        }
        result.addAll(addition);
        try( FileWriter writer = new FileWriter("src/main/resources/output.txt")) {
            for (String res: result) {
                writer.write(res + "\n");
            }
            writer.flush();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
