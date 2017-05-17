import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//my comment

public class Test {
	public void testPostInventoryData() throws Exception {
	    URL siteUrl = new URL("http://localhost:8080/UES/postInventoryData?region=LDN&env=PROD-UAT");
	    URLConnection conn = (URLConnection) siteUrl.openConnection();
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestProperty("Content-Type", "text/plain"); 
	    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	    String content = "E,B,C,D,E,F,G,H,I,J,K,L,M,N,O\nE,B,C,D,E,F,G,H,I,J,K,L,M,N,O";
	    
	    
	    System.out.println(content);
	    out.writeBytes(content);
	    out.flush();
	    out.close();
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line = "";
	    while((line=in.readLine())!=null) {
	        System.out.println(line);
	    }
	    in.close();
	}
	
	public void testJCTInventory() throws Exception {
	    URL siteUrl = new URL("http://localhost:8080/UES/JCTInventory?region=SGP&env=UAT");
	    URLConnection conn = (URLConnection) siteUrl.openConnection();
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestProperty("Content-Type", "text/plain"); 
	    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	    String content = "D,B,C,D,E,F,G\nA,B,C,D,E,F,G";
	    
	    
	    System.out.println(content);
	    out.writeBytes(content);
	    out.flush();
	    out.close();
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line = "";
	    while((line=in.readLine())!=null) {
	        System.out.println(line);
	    }
	    in.close();
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		try {
			t.testPostInventoryData();
			t.testJCTInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
