package com.timBuchalka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemCode {


    //    map of all channels available
    private Map<String, List<Double>> channels_map = new HashMap<String, List<Double>>();
    //    map of all metrics available
    private Map<String, Double> metrics_map = new HashMap<String, Double>();


    //constructor
    public SystemCode(String channelFile, String parameterFile) throws IOException {
        readChannelsFile(channelFile);
        readParameters(parameterFile);
    }

    //method for reading channels
    private void readChannelsFile(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        String key = "";
        List<Double> values = new ArrayList<Double>();
        int count = 0;

        String line;
        while ((line = br.readLine()) != null) {
            // split line using char and save it to map
            for (String token : line.split(",")) {
                if (!isNumber(token)) {

                    if (count != 0) {

                        channels_map.put(key, values);


                        values.clear();
                        key = token;

                    } else {
                        key = token;
                    }


                } else {

                    values.add(Double.parseDouble(token));
                }
                count++;

            }

        }


        br.close();

        channels_map.put(key, values);

    }


    //    method for reading parameters
    private void readParameters(String name) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(name));


        String line;
        String key = " ";
        int count = 0;
        double value = 0;

        while ((line = br.readLine()) != null) {
            // split line using char and save it to map
            for (String token : line.split(",")) {

                if (!isNumber(token)) {

                    if (count != 0) {

                        metrics_map.put(key, value);
                        key = token;
                    } else {
                        key = token;
                    }


                } else {

                    value = Double.parseDouble(token);
                }

                count++;

            }
        }
        metrics_map.put(key, value);
        br.close();



    }

    //    A method to check if the string is a number
    private boolean isNumber(String x) {


        try {

            Double.parseDouble(x);


        } catch (NumberFormatException e) {
            return false;

        }

        return true;

    }


    //get the metrics from the parameters map
    public double getMetrics(String key) {
        return metrics_map.get(key);
    }

    //get the channels from the channels map
    public List<Double> getChannel(String key) {
        return channels_map.get(key);
    }

//    function 1
    public void function_1(double m, double c, List<Double> X_channel) {

        List<Double> values = new ArrayList<Double>();

        for (Double aDouble : X_channel) {
            values.add((m * aDouble) + c);

        }
        channels_map.put("Y", values);

    }

    //    function 2
    public void function_2(List<Double> A, List<Double> Y) {
        double b = 0;

        List<Double> values = new ArrayList<Double>();
        int sum = 0;

        for (int i = 0; i < A.size(); i++) {
            values.add(A.get(i) + Y.get(i));
            sum += values.get(i);
        }
        b = sum / (double) values.size();

        channels_map.put("B", values);
        metrics_map.put("b", b);

    }

    //    function 3
    public void function_3(List<Double> X_channel) {
        List<Double> values = new ArrayList<Double>();

        for (Double aDouble : X_channel) {
            values.add(1.0 / aDouble);
        }
        channels_map.put("A", values);


    }


    //    function 4
    public void function_4(List<Double> X_channel, double b) {
        List<Double> values = new ArrayList<Double>();

        for (Double aDouble : X_channel) {
            values.add(aDouble + b);
        }

        channels_map.put("C", values);
    }

}
