
public class SumHexArray {

    public static void main(String[] args) {
        char[] a = {'7', '6', '7'};
        char[] b = {'1', 'A'};
        char[] c = {'3', 'B', '2', '4'};
        char[] d = {'6', 'E', 'F'};
        print(addHex(b, a));
        print(addHex(c, d));
        print(addHex(c, a));
    }

    public static char[] addHex(char[] r, char[] s) {
       // Left as exercise
       char hex = r[0];
       switch (hex) {
           case 'A':
        	   System.out.print("10");
               break;
           case 'B':
        	   System.out.print("11");
               break;
           case 'C':
               System.out.print("12");
               break; 
           case 'D':
               System.out.print("13");
               break;
           case 'E':
               System.out.print("14");
               break;
           case 'F':
               System.out.print("15");
               break;
       }
       return null;
    }
    
    
    public static void print(char[] result) {
        System.out.print("{");
        if (result[0] != '0') {
            System.out.print(result[0] + ", ");
        }
        for (int i = 1; i < result.length - 1; ++i) {
            System.out.print(result[i] + ", ");
        }
        System.out.println(result[result.length - 1] + "}");
    }

}

