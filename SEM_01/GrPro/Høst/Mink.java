import java.io.IOException;

public class Mink {
    public static void main(String[] args) {
        try {
            int h = readInt(), b = readInt(), d = readInt();
            String[] lines = new String[h];
            char[][] chararray = new char[h][b];
            for (int i = 0; i < h; i++) {
                // StringBuilder line = new StringBuilder();
                for (int j = 0; j < b; j++) {
                    chararray[i][j] =(char) System.in.read();
                    // line.append((char) System.in.read());
                }
                // lines[i] = line.toString();
                System.in.read(); // newline
            }

            for (int i = 1; i < h; i++) {
                int offset = 0;
                int minkStart = lines[i].substring(offset).indexOf("<") + offset;
                while (minkStart >= offset) {
                    int minkEnd = lines[i].substring(offset).indexOf(">") + offset;
                    int profit = d;
                    String[] newLines = new String[i+1];
                    for (int j = i; j >= 0; j--) {
                        StringBuilder line = new StringBuilder();
                        line.append(lines[j].substring(0, minkStart));
                        for (int x = minkStart; x <= minkEnd; x++) {
                            if (lines[j].charAt(x) == '#') {
                                profit--;
                            } else if (lines[j].charAt(x) != ' ' && j != i) {
                                // If another undug mink is found, add extra to profit
                                // but also to minkStart + minkEnd
                                minkStart = lines[j].substring(offset).indexOf("<") + offset;
                                minkEnd = lines[j].substring(offset).indexOf(">") + offset;
                                profit += d;
                            }
                            line.append(" ");
                        }
                        line.append(lines[j].substring(minkEnd+1));
                        newLines[j] = line.toString();
                    }
                    if (profit > 0) {
                        for (int j = i; j >= 0; j--) {
                            lines[j] = newLines[j];
                        }
                    }
                    offset = minkEnd;
                    minkStart = lines[i].substring(offset).indexOf("<") + offset;
                }
            }

            for (String l : lines) {
                System.out.println(l);
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