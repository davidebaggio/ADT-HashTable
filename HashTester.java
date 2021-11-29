import java.io.*;
import java.util.Scanner;
public class HashTester{
  public static void main(String[] args)throws IOException{
    Scanner in = new Scanner(new FileReader(args[0]));
    FileWriter o = new FileWriter("Output.txt");
    HashTable<String> tab = new HashTable<String>();
    while(in.hasNextLine()){
      tab.add(in.nextLine());
    }
    in.close();
    o.write("Gli oggetti ordinati sono: ");
    System.out.println("Gli oggetti ordinati sono: ");
    Comparable[] ar = tab.elementSet();
    for(int i = 0; i < ar.length; i++){
      o.write("\n" + (String)ar[i]);
      System.out.println((String)ar[i]);
    }
    o.close();
  }
}
