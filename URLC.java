import java.net.*;
import java.io.*;
import java.util.*;

class URLC {
	public static void main(String args[]) throws Exception {
		
		int c;
		URL url = new URL(args[0]);
		URLConnection urlc = url.openConnection();

		//get Date info
		long d = urlc.getDate();
		if(d == 0) 
			System.out.println("NO date info");
		else
			System.out.println("Date: "+new Date(d));

		//content type
		System.out.println("Content Type: "+urlc.getContentType());

		//expiration date
		d = urlc.getExpiration();
		if(d==0)
			System.out.println("No expiration info");
		else
			System.out.println("Expiration date : " + new Date(d));

		//last-mofified date
		d = urlc.getLastModified();
		if(d==0)
			System.out.println("No modificaion info");
		else
			System.out.println("Last Mofified: "+new Date(d));

		//content length
		long len = urlc.getContentLengthLong();
		String content = "";
		if(len == -1)
			System.out.println("Content Length unavailable");
		else
			System.out.println("Content length: "+len);

		if(len != 0) {
			System.out.println("===========Content===============");
			InputStream i = urlc.getInputStream();
			while( (c=i.read()) != -1) {
				System.out.print((char) c);
				content += (char) c;
			}
			i.close();
		}else {
			System.out.println("Content Unavailable");
		}
		String filename = url.getHost();
		FileWriter writer;
		if(args.length == 2) {
			if(args[1] != null && args[1] != "") {
				filename = args[1];	
			}
		}
		writer = new FileWriter(filename+".html");
		writer.write(content);
		writer.close();
	}
}
