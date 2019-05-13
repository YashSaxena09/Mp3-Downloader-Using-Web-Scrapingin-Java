package mp3downloader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Vivek Sharma
 */
public class MP3Downloader {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
      
        
        Scanner sc = new Scanner(System.in);
        
        
        String song = sc.nextLine();
        
        URL url = new URL("https://www.djmaza.link/search?q="+song.replace(" ", "+"));
        
        Document doc = Jsoup.parse(url,10000);
        
        Element e = doc.selectFirst("body > section > main > content > div > div.single-songs.list-group > figure:nth-child(1) > div > a");
        
        String surl2 = "https://www.djmaza.link" +e.attr("href");
        
        URL url1 = new URL(surl2);
        
        Document doc2 = Jsoup.parse(url1,10000);
        
        Element e2 = doc2.selectFirst("body > section > main > content > div.page-down-wrap > div.page-down-body > ul > li > a");
        
        String downloadlink = e2.attr("href");
        
        System.out.println(downloadlink);
        
        URL url2 = new URL(downloadlink);

       HttpURLConnection con = (HttpURLConnection) url2.openConnection();
       con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
       InputStream is = con.getInputStream();
       
       FileOutputStream out = new FileOutputStream("Song.mp3");
       
       int i=0;
       
       while((i=is.read())!=-1)
       {
           out.write(i);
           
       }
       out.close();
    }
    
    
}