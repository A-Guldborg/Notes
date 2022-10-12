import java.util.*;

public class Problem_N{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    
        int b = sc.nextInt(), h = sc.nextInt();
        sc.nextLine();
        boolean startFound = false;
        boolean endFound = false;
        
        int startY = 0;
        int endY = 0;
        int x = 0;
        int y = 0;
        
        for(int i = 0; i < h; i++){
            String input = sc.nextLine();
            
            if(input.contains("#") && !startFound){
                startFound = true;
                startY = i;
                
                int first = input.indexOf("#");
                int last = input.lastIndexOf("#");
                
                x = (first + last) / 2;
            }
            
            if(!input.contains("#") && startFound){
                endY = i-1;
                endFound = true;
                y = (startY + endY) / 2;
                break;
            }
            
            if(i == h-1){
                if (endFound == false) {
                    endY = i-1;
                }
                break;
            }
            
        }
        y = (int)Math.ceil(((double)startY + (double)endY) / 2);
        System.out.println(x + " " + y);
    }
}