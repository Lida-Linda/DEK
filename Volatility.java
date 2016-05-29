import java.io.*;
import java.lang;
import java.util.ArrayList;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.BufferedReader;


public class Volatility
{
  private static List<Character> chars = new ArrayList<>();
  
  public static void main (String[] args)
  {
    BufferedReader reader = null;
    try 
    {
      reader = new BufferedReader(new FileReader(new File("data.txt")));
      int c;
      while ((c = reader.read()) != -1) // Коли дійдемо до кінця файлу отримаємо -1
      {
        chars.add((char) c);
      }
      reader.close();
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    } 
    finally 
    {
      if (reader != null) 
      {
        try 
        {
          reader.close();
        }
        catch (IOException e) 
        {
          e.printStackTrace();
        }
    }
    
    FileInputStream myfile = new FileInputStream("data.txt");
    
    
  }
}
