package app11;

public class String1 {

	public static void main(String[] args) {
		boolean stmt="champ"=="champ";
		boolean stmt2=new String("champ")=="champ";
		boolean stmt3=new String("champ")==new String("champ");
		System.out.println(stmt);
		System.out.println(stmt2);
		System.out.println(stmt3);
		System.out.println(stmt&&stmt2||stmt3); 
		

	}

}
