import net.bytebuddy.agent.builder.AgentBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLink {
    public static void main (String a[]) throws MalformedURLException {
        WebDriver driver= new ChromeDriver();
        driver.get("http://newtours.demout.com/");
        driver.manage().window().maximize();
        //capture the link from web page
        List<WebElement> links =driver.findElements(By.tagName("a"));
        System.out.println("total no of links :" +links.size());

        for(int i=0;i<links.size();i++) {
            WebElement element=links.get(i);
           String url=element.getAttribute("href");

           URL link=new URL(url);

           //create url connection
            HttpURLConnection httConn =(HttpURLConnection)link.openConnection();
            httConn.connect();
            int rescode=httConn.getResponseCode();

            if(rescode>=400){
                System.out.println(url + " "+"  is broken");
            }
            else{
                System.out.println(url + " "+"  valid link");
            }
        }
    }
}
