package com.company.servicenowtests;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;


public class ScreenshotUtils {
    
	public static String currentDir = System.getProperty("user.dir");
	
	@Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] captureSnapshotForAllure(WebDriver driver)/* throws IOException */ {
		        try {
		            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		            return Files.toByteArray(screen);
		        } catch (IOException e) {
		        	return null;
		        }
		    }
		}

