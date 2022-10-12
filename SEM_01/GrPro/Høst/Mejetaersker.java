import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class Mejetaersker {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] input = s.next().toCharArray();
        HashMap<Integer, Integer[]> move = new HashMap<Integer, Integer[]>();
        init(move);
        int maxy = 0, maxx = 0, miny = 0, minx = 0, currx = 0, curry = 0;
        int dir = 0; // north, east, south, west from 0 to 3
        ArrayList<Integer[]> coords = new ArrayList<Integer[]>();
        coords.add(new Integer[]{0, 0});
        for (char c : input) {
            if (c == '>') {
                // turn right
                dir = (dir+1) % 4;
            } else if (c == '<') {
                // turn left
                if (dir == 0) {dir = 3;} else {dir--;}
            } else {
                // move straight
                Integer[] moves = move.get(dir);
                currx += moves[0];
                curry += moves[1];
                maxx = Math.max(maxx, currx);
                maxy = Math.max(maxy, curry);
                minx = Math.min(minx, currx);
                miny = Math.min(miny, curry);
                coords.add(new Integer[]{currx, curry});
            }
        }
        int offsetx = Math.abs(minx);
        int offsety = Math.abs(miny);
        char[][] output = new char[maxx + 1 + offsetx][];
        for (int i = 0; i <= maxx + offsetx; i++) {
            char[] line = new char[maxy + 1 + offsety];
            Arrays.fill(line, ' ');
            output[i] = line;
        }
        for (Integer[] coordset : coords) {
            int x = coordset[0] + offsetx;
            int y = coordset[1] + offsety;
            output[x][y] = '#';
        }
        for (char[] line : output) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    private static void init(HashMap hm) {
        Integer[] m0 = {-1, 0};
        Integer[] m1 = {0, 1};
        Integer[] m2 = {1, 0};
        Integer[] m3 = {0, -1};
        hm.put(0, m0);
        hm.put(1, m1);
        hm.put(2, m2);
        hm.put(3, m3);
    }
}