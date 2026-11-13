import java.net.*;
import java.io.*;
import java.util.Scanner;

public class linkPuller {
    public static void main(String[] args) {
        linkPuller app= new linkPuller();
    }
    public linkPuller(){
// renamed all the variables
        try{
            Scanner input = new Scanner(System.in);
            String again = "yes";

           // while (again.equals("yes")) {
         //       try {
            System.out.println();
            URL websiteURL = new URL("https://www.espn.com/"); // insert url
            URLConnection websiteConnection = websiteURL.openConnection();
            websiteConnection.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            BufferedReader HTMLreader = new BufferedReader(
                    new InputStreamReader(websiteConnection.getInputStream()));
            String htmlLine;

            String filter = "espn"; // only prints links containing this word

            while ((htmlLine = HTMLreader.readLine()) != null) { // prints milton/link/espn
                //reads websites html
                // add while instead of if
                String orginalLine = htmlLine;
                while (htmlLine.contains("href=\"")) { // finds links that use double qoutes
                    int linkStart = htmlLine.indexOf("href=\"")+6; // skips first 6 of href
                    int linkEnd = htmlLine.indexOf("\"", linkStart); // start of links

                if (linkEnd > linkStart) { // end > start
                    String link = htmlLine.substring(linkStart, linkEnd); // string link
                    if (link.contains(filter)) { // prints the link if it has the word in the filter
                        System.out.println(link); // prints link
                    }
                    htmlLine = htmlLine.substring(linkEnd + 1);
                }else {
                    break;
                    }
                }
                htmlLine = orginalLine;
                while (htmlLine.contains("href='")) { // finds links that have single qoutes
                    int linkStart = htmlLine.indexOf("href='") + 6; // skips past 6 characters of href-=  to start at the link
                    int linkEnd = htmlLine.indexOf("'", linkStart); // end of the link

                    if (linkEnd > linkStart) {
                        String link = htmlLine.substring(linkStart, linkEnd); // extracts link
                        if (link.contains(filter)) { // only prints when it has the word in the filter
                            System.out.println(link); // prints link
                        }
                        htmlLine = htmlLine.substring(linkEnd + 1);
                    } else {
                        break;
                    }


                }

            }
            HTMLreader.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }



}
// test