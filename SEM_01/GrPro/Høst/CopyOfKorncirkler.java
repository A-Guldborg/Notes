import java.util.*;
public class CopyOfKorncirkler {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int b = s.nextInt(), h = s.nextInt();
        String[] lines = new String[h];
        // ArrayList<Integer> xcoords = new ArrayList<Integer>();
        HashMap<Integer, Integer> xy = new HashMap<Integer, Integer>();
        for (int i = 0; i < h; i++) {
            String line = s.next();
            int offset = 0;
            for (Integer key : new ArrayList<Integer>(xy.keySet())) {
                if (line.charAt(key) == '.' || i == h-1) {
                    // end of field, x is key
                    int y = (xy.get(key) + i - 1) / 2;
                    System.out.println(key + " " + y);
                    xy.remove(key);
                }
            }
            while (true) {
                int idx = line.indexOf("#");
                if (idx >= 0) {
                    int x = line.substring(idx).indexOf(".");
                    if (x >= 0) {
                        line = line.substring(x);
                        offset += x;
                        x = (idx+x-1 + offset)/2; // middle of #'s. 
                    } else {
                        x = (idx+b-1 + offset)/2; // middle of #'s.
                    }
                    xy.putIfAbsent(x, i);
                } else {
                    break;
                }
            }
        }
    }
}
