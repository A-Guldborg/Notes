---
title: Mink
---

```java
import java.io.IOException;

public class CopyOfMink {
    public static void main(String[] args) {
        try {
            int h = readInt(), b = readInt(), d = readInt();

            char[][] chararray = new char[h][b];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < b; j++) {
                    chararray[i][j] = (char) System.in.read();
                }
                System.in.read(); // newline
            }

            for (int i = 1; i < h; i++) {
                int minkStart = -1, minkEnd = -1;
                char[][] temparray = new char[h][b];
                for (int k = 0; k < h; k++) {
                    temparray[k] = chararray[k].clone();
                }
                for (int j = 1; j < b; j++) {
                    if (chararray[i][j] == '<') {
                        minkStart = j;
                        temparray[i][j] = ' ';
                    } else if (chararray[i][j] == '=') {
                        temparray[i][j] = ' ';
                    } else if (chararray[i][j] == '>') {
                        minkEnd = j;
                        int profit = d;
                        temparray[i][j] = ' ';
                        // check upwards from here
                        for (int x = i; x >= 0; x--) {
                            for (int y = minkStart; y <= minkEnd; y++) {
                                if (temparray[x][y] == '<' || temparray[x][y] == '=' || temparray[x][y] == '>') {
                                    profit += d; // Add profit for new mink
                                    int t = y;
                                    while (temparray[x][t] != '#') {
                                        temparray[x][t] = ' ';
                                        t--;
                                    }
                                    minkStart = Math.min(minkStart, t+1);
                                    t = y;
                                    while (temparray[x][t] != '#') {
                                        temparray[x][t] = ' ';
                                        t++;
                                    }
                                    minkEnd = Math.max(minkEnd, t-1);
                                } else if (temparray[x][y] == '#') {
                                    profit--;
                                    temparray[x][y] = ' ';
                                }
                            }
                        }
                        if (profit > 0) {
                            for (int k = 0; k < h; k++) {
                                chararray[k] = temparray[k].clone();
                            }
                        }
                    }
                }
            }

            for (char[] arr : chararray) {
                System.out.println(String.valueOf(arr));
            }
        }catch (IOException ioe){ioe.printStackTrace();}
    }

    private static int readInt() throws IOException {
        // Credit jbarrameda on stackoverflow for input reader
        int ret = 0;
        boolean dig = false;
        for (int c = 0; (c = System.in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig) break;
        }
        return ret;
    }
}
```
