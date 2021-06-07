package com.timBuchalka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        SystemCode sc= new SystemCode("channels.txt","parameters.txt");
         List<Double> X_channel = sc.getChannel("X");
         double m=sc.getMetrics("m"), c=sc.getMetrics("c"),b=0;

         sc.function_1(m,c,X_channel);
        List<Double> Y_channel= sc.getChannel("Y");

        sc.function_3(X_channel);
        List<Double> A_channel= sc.getChannel("A");

        sc.function_2(A_channel,Y_channel);
        List<Double> B_channel= sc.getChannel("B");
        b= sc.getMetrics("b");



        sc.function_4(X_channel,b);


        System.out.println("The value of metric b is: " + b);




//        sc.readChannelsFile("parameters.txt");
    }
}
