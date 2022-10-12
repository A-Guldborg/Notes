import java.util.Scanner;
import java.util.ArrayList;
public class Mink2 {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            int height = s.nextInt(), width = s.nextInt(), bounty = s.nextInt();
            char[][] field = new char[height][width];
            ArrayList<Coordset> coords = new ArrayList<Coordset>();
            int y = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    char c = (char) System.in.read();
                    field[i][j] = c;
                    if (c == '<') {
                        y = j;
                    } else if (c == '>') {
                        coords.add(new Coordset(i, y, j));
                    }
                }
                System.in.read(); // newline
            }

            for (Coordset c : coords) {
                if (c.x == 1) {
                    int length = c.endy - c.starty + 1;
                    int prof = bounty - c.x * length;
                    if (prof > 0) {
                        for (int x = c.x; x >= 0; x--) {
                            for (int i = c.starty; i <= c.endy; i++) {
                                field[x][i] = ' ';
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < height; i++) {
                StringBuilder out = new StringBuilder();
                for (int j = 0; j < width; j++) {
                    out.append(field[i][j]);
                }
                System.out.println(out);
            }
        } catch (Exception e) {}
    }

    private static class Coordset {
        private int x;
        private int starty;
        private int endy;
        private Coordset(int x, int starty, int endy) {
            this.x = x;
            this.starty = starty;
            this.endy = endy;
        }
    }
}