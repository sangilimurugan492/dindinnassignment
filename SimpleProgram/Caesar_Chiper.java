import java.util.*;
public class Caesar_Chiper
{
  public static void main (String[]args)
  {
    Scanner scanner = new Scanner (System.in);
	System.out.println ( "Please Enter Raw String");
    String rawString = scanner.next ();
	System.out.println ( "Please Enter Encryption Shift Value");
    int shift = scanner.nextInt ();
    System.out.println (encrypt (rawString, shift));
  }

  private static String encrypt (String rawString, int shift)
  {
    StringBuffer s = new StringBuffer ();

  for (char c:rawString.toCharArray ())
      {
	if (Character.isUpperCase (c))
	  
	    s.append (Character.
		      toString ((char) (((c - 'A' + shift) % 26) + 'A')));
	  
	else
	  
	    s.append (Character.
		      toString ((char) (((c - 'a' + shift) % 26) + 'a')));
	  
      }
    return s.toString ();

  }

}
