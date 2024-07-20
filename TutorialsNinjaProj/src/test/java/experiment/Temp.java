package experiment;
import java.util.*;

public class Temp {

	public static void main(String[] args) {
		
		timeStamp1("ra  vi:k@u,m.a  r+shar");

	}
	public static void  timeStamp1(String a)
	{
		//String abc= "ra  vi:ku,ma  r+shar";
		String x = a.replaceAll("[:+,.@ ]", "_");
		System.out.println(x);
	}

}
