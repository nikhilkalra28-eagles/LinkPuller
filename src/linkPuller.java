import java.net.*;
import java.io.*;
import java.util.Scanner;

public class linkPuller {
    public static void main(String[] args) {
        linkPuller app= new linkPuller(); // renamed the project to linkPuller
    }
    public linkPuller() {
// renamed all the variables
        Scanner input = new Scanner(System.in); // found this when I researched on google // reads various sources
        String again = "yes"; // creates a string yes
        while (again.equals("yes")) { // project
            try {
                System.out.println(); // system prints
                System.out.print("What website would you like to extract links from? (paste the link)"); // asks the user what input they would like to use
                String website = input.nextLine();
                URL websiteURL = new URL(website); // insert url
                URLConnection websiteConnection = websiteURL.openConnection();
                websiteConnection.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
                BufferedReader HTMLreader = new BufferedReader(
                        new InputStreamReader(websiteConnection.getInputStream()));
                String htmlLine;

               // String filter = "espn"; // only prints links containing this word (old)
                System.out.println("What word would you like to filter the links by (example: espn)"); // new filter for the word and asks the user
                String filter = input.nextLine(); // filter

                while ((htmlLine = HTMLreader.readLine()) != null) { // prints milton/link/espn
                    //reads websites html
                    // add while instead of if
                    String orginalLine = htmlLine;
                    while (htmlLine.contains("href=\"")) { // finds links that use double qoutes
                        int linkStart = htmlLine.indexOf("href=\"") + 6; // skips first 6 of href
                        int linkEnd = htmlLine.indexOf("\"", linkStart); // start of links

                        if (linkEnd > linkStart) { // end > start
                            String link = htmlLine.substring(linkStart, linkEnd); // string link
                            if (link.contains(filter)) { // prints the link if it has the word in the filter
                                System.out.println(link); // prints link
                            }
                            htmlLine = htmlLine.substring(linkEnd + 1); // keeps only the stuff one after the linkEnd
                        } else {
                            break;
                        }
                    }
                    htmlLine = orginalLine;
                    while (htmlLine.contains("href='")) { // finds links that have single qoutes
                        int linkStart = htmlLine.indexOf("href='") + 6; // skips past 6 characters of href-=  to start at the link
                        int linkEnd = htmlLine.indexOf("'", linkStart); // end of the link

                        if (linkEnd > linkStart) { // link end > start
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
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("Your link does not work. Try again with another link. "); // spicy extension
            }
            System.out.print("Would you like to run the program again? (yes or no)"); // asks user what they would like to print
            again = input.nextLine();
        }


    }
}

// test