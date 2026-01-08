
import java.util.List;

public class TestMyLinkedList {
	
   public static void main(String[] args) {
      MyLinkedList<Integer> list1 = new MyLinkedList<>();
      for(int i=0;i<10;++i) {
         list1.addLast(((int)(Math.random()*1000)+10));
      }
   		  
      System.out.println(list1);
      list1.remove(2);
      list1.add(2,2000);
      System.out.println(list1);
      
      list1.writeBackWard();
   	  	  
   }  
}