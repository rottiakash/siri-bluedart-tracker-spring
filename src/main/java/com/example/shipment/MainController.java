package com.example.shipment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class MainController {
    
    @GetMapping
    public String Index()
    {
        WebDriver driver = new ChromeDriver();
		driver.get("https://www.bluedart.com/tracking");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("trackingNo")).sendKeys("*TRACKING ID*");
        driver.findElement(By.id("goBtn")).click();
        driver.findElement(By.xpath("//*[@id=\"AWB38962265650\"]/div/div[2]/div/div/ul/li[2]/a")).click();
        List<WebElement> rows =  driver.findElement(By.xpath("//*[@id=\"SCAN38962265650\"]/div/table/tbody")).findElements(By.tagName("tr"));
        String place = rows.get(0).findElements(By.tagName("td")).get(0).getText();
        String update = rows.get(0).findElements(By.tagName("td")).get(1).getText();
        String date = rows.get(0).findElements(By.tagName("td")).get(2).getText();
        String time = rows.get(0).findElements(By.tagName("td")).get(3).getText();
        String result = String.format("The last update is %s at %s on %s %s", update,place,date,time);
        driver.close();
        return result;
    }
}
