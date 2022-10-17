---
title: Regnskab
---

```java
import java.util.Scanner;
import java.util.HashMap;

public class Regnskab {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        initializeHashMap(map); // Add all possible numbers to the map
        String[] input = s.nextLine().split(" ");
        int sum = 0;
        int tempsum = 0;
        int temptempsum = 0; // unoriginal and bad variable names
        int bigNumber = 1;
        int tempBigNumber = 1;
        for (int i = input.length - 1; i >= 0; i--) {
            int number;
            if (input[i].contains("og")) {
                String[] temp = input[i].split("og");
                number = map.get(temp[0]) + map.get(temp[1]);
            } else {
                number = map.get(input[i]);
            }
            if (number % 100 == 0) {
                if (number > bigNumber) {
                    tempsum += tempBigNumber * temptempsum;
                    sum += tempsum * bigNumber;
                    temptempsum = 0;
                    tempsum = 0;
                    bigNumber = number;
                    tempBigNumber = 1;
                } else {
                    tempsum += temptempsum * tempBigNumber;
                    temptempsum = 0;
                    tempBigNumber = number;
                }
            } else {
                temptempsum += number;
            }
        }
        tempsum += temptempsum * tempBigNumber;
        sum += tempsum * bigNumber;
        System.out.println(sum);
    }
    
    
    
    
    
    private static void initializeHashMap(HashMap hm) {
        hm.put("en", 1);
        hm.put("et", 1);
        hm.put("to", 2);
        hm.put("tre", 3);
        hm.put("fire", 4);
        hm.put("fem", 5);
        hm.put("seks", 6);
        hm.put("syv", 7);
        hm.put("otte", 8);
        hm.put("ni", 9);
        hm.put("ti", 10);
        hm.put("elleve", 11);
        hm.put("tolv", 12);
        hm.put("tretten", 13);
        hm.put("fjorten", 14);
        hm.put("femten", 15);
        hm.put("seksten", 16);
        hm.put("sytten", 17);
        hm.put("atten", 18);
        hm.put("nitten", 19);
        hm.put("tyve", 20);
        hm.put("tredive", 30);
        hm.put("fyrre", 40);
        hm.put("halvtreds", 50);
        hm.put("tres", 60);
        hm.put("halvfjerds", 70);
        hm.put("firs", 80);
        hm.put("halvfems", 90);
        hm.put("hundrede", 100);
        hm.put("tusinde", 1000);
        hm.put("million", 1000000);
        hm.put("millioner", 1000000);
    }
}
```
