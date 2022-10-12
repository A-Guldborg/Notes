import java.util.*;
public class Korncirkler {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int b = s.nextInt(), h = s.nextInt();
        String[] lines = new String[h];
        ArrayList<Integer> currentxes = new ArrayList<Integer>();

        for (int i = 0; i < h; i++) {
            lines[i] = s.next();
        }

        for (int y = 0; y < h; y++) {
            // Remove current x'es
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < currentxes.size(); i++) {
                if (lines[y].charAt(currentxes.get(i)) == '#') {
                    temp.add(currentxes.get(i));
                }
            }
            currentxes = temp;
            
            String currentline = lines[y];
            int offset = 0;
            int fieldstart = currentline.indexOf("#");
            while (fieldstart != -1) {
                int fieldlength = currentline.substring(fieldstart).indexOf(".");
                if (fieldlength == -1) {
                    fieldlength = b - fieldstart - offset;
                }
                int centerx = (fieldlength -1) / 2 + fieldstart + offset;
                if (!currentxes.contains(centerx)) {
                    currentxes.add(centerx);
                    int ypos = y + 1;
                    while (ypos < h - 1 && lines[ypos].charAt(centerx) == '#') {
                        ypos++;
                    }
                    System.out.println(centerx + " " + ((y + ypos) / 2));
                }
                offset += fieldstart + fieldlength;
                if (offset != b) {
                    currentline = lines[y].substring(offset);
                    fieldstart = currentline.indexOf("#");
                } else {
                    break;
                }
            }
        }
    }
}
