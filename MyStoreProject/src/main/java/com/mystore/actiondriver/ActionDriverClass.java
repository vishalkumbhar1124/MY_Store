package com.mystore.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mystore.actioninterface.ActionInterface;
import com.mystore.base.BaseClass;

/**
 * @author Hitendra Verma
 * added on 13th March 2019
 */
public class ActionDriverClass extends BaseClass implements ActionInterface {

    @Override
    public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    @Override
    public void click(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).click().build().perform();
    }

    @Override
    public boolean findElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            flag = ele.isDisplayed();
        } catch (Exception e) {
            System.out.println("Unable to locate element");
        }
        return flag;
    }

    @Override
    public boolean isDisplayed(WebDriver driver, WebElement ele) {
        boolean flag = findElement(driver, ele) && ele.isDisplayed();
        System.out.println(flag ? "The element is Displayed" : "The element is not Displayed");
        return flag;
    }

    @Override
    public boolean isSelected(WebDriver driver, WebElement ele) {
        boolean flag = findElement(driver, ele) && ele.isSelected();
        System.out.println(flag ? "The element is Selected" : "The element is not Selected");
        return flag;
    }

    @Override
    public boolean isEnabled(WebDriver driver, WebElement ele) {
        boolean flag = findElement(driver, ele) && ele.isEnabled();
        System.out.println(flag ? "The element is Enabled" : "The element is not Enabled");
        return flag;
    }

    @Override
    public boolean type(WebElement ele, String text) {
        boolean flag = false;
        try {
            ele.clear();
            ele.sendKeys(text);
            flag = true;
        } catch (Exception e) {
            System.out.println("Unable to enter value");
        }
        return flag;
    }

    @Override
    public boolean selectBySendkeys(String value, WebElement ele) {
        boolean flag = false;
        try {
            ele.sendKeys(value);
            flag = true;
        } catch (Exception e) {
            System.out.println("Not Selected value from the DropDown");
        }
        return flag;
    }

    @Override
    public boolean selectByIndex(WebElement element, int index) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByIndex(index);
            flag = true;
        } catch (Exception e) {
            System.out.println("Option not selected by Index");
        }
        return flag;
    }

    @Override
    public boolean selectByValue(WebElement element, String value) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByValue(value);
            flag = true;
        } catch (Exception e) {
            System.out.println("Option not selected by Value");
        }
        return flag;
    }

    @Override
    public boolean selectByVisibleText(String visibletext, WebElement ele) {
        boolean flag = false;
        try {
            Select s = new Select(ele);
            s.selectByVisibleText(visibletext);
            flag = true;
        } catch (Exception e) {
            System.out.println("Option not selected by VisibleText");
        }
        return flag;
    }

    @Override
    public boolean mouseHoverByJavaScript(WebElement ele) {
        boolean flag = false;
        try {
            String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                                "evObj.initMouseEvent('mouseover',true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                                "arguments[0].dispatchEvent(evObj);";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(javaScript, ele);
            flag = true;
        } catch (Exception e) {
            System.out.println("MouseOver Action is not performed");
        }
        return flag;
    }

    @Override
    public boolean JSClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
            flag = true;
        } catch (Exception e) {
            System.out.println("Click Action is not performed");
        }
        return flag;
    }

    @Override
    public boolean switchToFrameByIndex(WebDriver driver, int index) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
            driver.switchTo().frame(index);
            flag = true;
        } catch (Exception e) {
            System.out.println("Frame with index \"" + index + "\" is not selected");
        }
        return flag;
    }

    @Override
    public boolean switchToFrameById(WebDriver driver, String idValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(idValue);
            flag = true;
        } catch (Exception e) {
            System.out.println("Frame with Id \"" + idValue + "\" is not selected");
        }
        return flag;
    }

    @Override
    public boolean switchToFrameByName(WebDriver driver, String nameValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(nameValue);
            flag = true;
        } catch (Exception e) {
            System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
        }
        return flag;
    }

    @Override
    public boolean switchToDefaultFrame(WebDriver driver) {
        boolean flag = false;
        try {
            driver.switchTo().defaultContent();
            flag = true;
        } catch (Exception e) {
            System.out.println("The Frame is not selected");
        }
        return flag;
    }

    @Override
    public void mouseOverElement(WebDriver driver, WebElement element) {
        try {
            new Actions(driver).moveToElement(element).build().perform();
            System.out.println("MouseOver Action is performed");
        } catch (Exception e) {
            System.out.println("MouseOver action is not performed");
        }
    }

    @Override
    public boolean moveToElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
            Actions actions = new Actions(driver);
            actions.moveToElement(ele).build().perform();
            flag = true;
        } catch (Exception e) {
            System.out.println("Move to element failed");
        }
        return flag;
    }

    @Override
    public boolean mouseover(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(ele).build().perform();
            flag = true;
        } catch (Exception e) {
            System.out.println("Mouseover action failed");
        }
        return flag;
    }

    @Override
    public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(source, x, y).build().perform();
            flag = true;
        } catch (Exception e) {
            System.out.println("Draggable action is not performed");
        }
        return flag;
    }

    @Override
    public boolean draganddrop(WebDriver driver, WebElement source, WebElement target) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDrop(source, target).perform();
            flag = true;
        } catch (Exception e) {
            System.out.println("Drag and drop action is not performed");
        }
        return flag;
    }

    @Override
    public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(ele, x, y).build().perform();
            flag = true;
        } catch (Exception e) {
            System.out.println("Slider action is not performed");
        }
        return flag;
    }

    @Override
    public boolean rightclick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).contextClick(ele).perform();
            flag = true;
        } catch (Exception e) {
            System.out.println("Right click action is not performed");
        }
        return flag;
    }

    @Override
    public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
        boolean flag = false;
        try {
            Set<String> windows = driver.getWindowHandles();
            String[] array = windows.toArray(new String[0]);
            driver.switchTo().window(array[count - 1]);
            if (driver.getTitle().contains(windowTitle)) {
                flag = true;
            } else {
                System.out.println("Window with title " + windowTitle + " is not found");
            }
        } catch (Exception e) {
            System.out.println("Switch to window by title failed");
        }
        return flag;
    }

    @Override
    public boolean switchToNewWindow(WebDriver driver) {
        boolean flag = false;
        try {
            Set<String> handles = driver.getWindowHandles();
            String newWindow = handles.toArray(new String[0])[handles.size() - 1];
            driver.switchTo().window(newWindow);
            flag = true;
        } catch (Exception e) {
            System.out.println("Switch to new window failed");
        }
        return flag;
    }

    @Override
    public boolean switchToOldWindow(WebDriver driver) {
        boolean flag = false;
        try {
            Set<String> handles = driver.getWindowHandles();
            String oldWindow = handles.toArray(new String[0])[0];
            driver.switchTo().window(oldWindow);
            flag = true;
        } catch (Exception e) {
            System.out.println("Switch to old window failed");
        }
        return flag;
    }

    @Override
    public int getColumncount(WebElement row) {
        List<WebElement> columns = row.findElements(By.tagName("td"));
        return columns.size();
    }

    @Override
    public int getRowCount(WebElement table) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.size();
    }

    @Override
    public boolean Alert(WebDriver driver) {
        boolean flag = false;
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            flag = true;
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present");
        } catch (Exception e) {
            System.out.println("Error occurred while handling alert");
        }
        return flag;
    }

    @Override
    public boolean launchUrl(WebDriver driver, String url) {
        boolean flag = false;
        try {
            driver.navigate().to(url);
            flag = true;
        } catch (Exception e) {
            System.out.println("URL did not launch");
        }
        return flag;
    }

    @Override
    public boolean isAlertPresent(WebDriver driver) {
        boolean flag = false;
        try {
            driver.switchTo().alert();
            flag = true;
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present");
        }
        return flag;
    }

    @Override
    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    @Override
    public boolean click1(WebElement locator, String locatorName) {
        boolean flag = false;
        try {
            locator.click();
            flag = true;
        } catch (Exception e) {
            System.out.println("Click action failed on " + locatorName);
        }
        return flag;
    }

    @Override
    public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void implicitWait(WebDriver driver, int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    @Override
    public void explicitWait(WebDriver driver, WebElement element, int timeOut) {
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void pageLoadTimeOut(WebDriver driver, int timeOut) {
        driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
    }

    @Override
    public String takeScreenshot(WebDriver driver, String filename) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + filename + "_" + dateName + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            System.out.println("Screenshot capture failed");
        }
        return destination;
    }

    @Override
    public String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    }
}
