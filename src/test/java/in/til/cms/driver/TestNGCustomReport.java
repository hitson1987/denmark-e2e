package in.til.cms.driver;

import static in.til.cms.driver.BaseClass.driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestNGCustomReport extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        ScreenShot(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        System.out.print("Class: " + testContext.getName() + "\n");
    }

    @Override
    public void onTestStart(ITestResult result) {
        //Do Nothing
    }

    @Override
    public void onConfigurationFailure(ITestResult tr) {
       try {       
        ScreenShot(tr);
        }catch (UnhandledAlertException e){
          driver.switchTo().alert().accept();  
        }catch(Exception e){
            //something happened. ignoring so that test continues
        }
    }
    
    //Capture screenshot on TestFailure
    public void ScreenShot(ITestResult result) {

        try {

            String NewFileNamePath;
            String methodName = result.getName();
            
            Object[] x = result.getParameters();
            
            //Get current date time with Date() to create unique file name
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "ddMMMyy__hhmmaa");
            // get current date time with Date()
            Date date = new Date();
            		System.out.println(new File(BaseClass.directory.getCanonicalPath()+ File.separator+ "reports"));
            if (!(new File(BaseClass.directory.getCanonicalPath() + File.separator + "reports" + File.separator + "screenshots")).exists()) {
                new File(BaseClass.directory.getCanonicalPath() + File.separator + "reports" + File.separator + "screenshots").mkdir();
            }

            NewFileNamePath = BaseClass.directory.getCanonicalPath() + File.separator + "reports" + File.separator + "screenshots"
                    + File.separator + methodName + "_" + dateFormat.format(date) + ".png";
            System.out.println(NewFileNamePath);    
            
          
         try{          
        	 File screenshot = ((TakesScreenshot) BaseClass.driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(NewFileNamePath));
            
            
         } catch (Exception e){
        	 e.printStackTrace();
         }
            Reporter.log(methodName + " failed; Click on image to enlarge<br/>"
                    + "<a target=\"_blank\" href=\"" + NewFileNamePath + "\"><img src=\"file:///" + NewFileNamePath
                  + "\" alt=\"\"" + "height='100' width='100'/></a><br />");
            Reporter.setCurrentTestResult(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}