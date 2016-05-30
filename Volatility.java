import java.io.*;
import java.lang;
import java.util.ArrayList;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.BufferedReader;


public class Volatility
{
  
  public void write(String st) throws IOException {
    // открываем поток ввода в файл
   outputStream = new FileOutputStream(path);
 
    // записываем данные в файл, но 
    // пока еще данные не попадут в файл,
    // а просто будут в памяти
    outputStream.write(st.getBytes());
    // только после закрытия потока записи, 
    // данные попадают в файл
    outputStream.close();
  }
 private static List<Character> chars = new ArrayList<>();
  
  public static void main (String[] args) {
    BufferedReader reader = null;
    
    try {
      reader = new BufferedReader(new FileReader(new File("data.txt")));
      int c;
      while ((c = reader.read()) != -1) { // Коли дійдемо до кінця файлу отримаємо -1
        chars.add((char) c);
      }
      reader.close();
    } 
    
    catch (IOException e) {
      e.printStackTrace();
    } 
    
    finally {
      if (reader != null) {
        try {
          reader.close();
        }
        
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      //створюмо файл
      File newFile = new File("Volatility.txt");
      //формула
     double g,a;
     int m=50; //вікно
     for (c = 1; c <= args.length-1; c++) {
        g[c]=Math.log(a[c+1])-Math.log(a[c]); //прибутковості
        
        //write()
     }
    
    //FileInputStream myfile = new FileInputStream("data.txt");
    
  }
}
