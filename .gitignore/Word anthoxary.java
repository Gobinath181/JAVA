import java.util.*;
public class MyClass {
    public static void main(String args[]) {
        int k;
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();
        String s2 = s.nextLine();
        s1.toLowerCase();
        s2.toLowerCase();
        while(s1.charAt(s1.length()-1)==s2.charAt(0))
        {
            s1 = s2;
            String s3 = s.nextLine();
            s3.toLowerCase();
            s2=s3;
        }
        if(s1.charAt(s1.length()-1)!=s2.charAt(0))
        System.out.println("Game over");
    }
}
