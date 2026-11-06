import java.net.*;
import java.io.*;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();
    }
    public HtmlRead(){

        try{
            System.out.println();
            URL fred = new URL("https://www.milton.edu/");
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("href=\"")) {
                    int start = line.indexOf("href=\"")+6;
                    int end = line.indexOf("\"", start);

                if (end > start){
                    String link = line.substring(start,end);
                    System.out.println(link);
                    }
                }
            }
            reader.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }



}