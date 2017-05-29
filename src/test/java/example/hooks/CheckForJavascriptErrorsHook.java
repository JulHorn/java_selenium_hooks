package example.hooks;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.ArrayList;

/*
* Checks if a javascript error occurred on a page via window.onerror. Does not work, if the javascript
* error occurred while loading the page.
**/
public class CheckForJavascriptErrorsHook implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        checkForJSError(driver);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        checkForJSError(driver);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }

    /**
     * Checks if a javascript error occurred on a page via window.onerror. Does not work, if the javascript
     * error occurred while loading the page.
     *
     * @param webDriver Used to execute the error checking javascript code.
     **/
    private void checkForJSError(WebDriver webDriver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object errorResult;
        boolean isAlreadyInjectedInPage = (Boolean) jsExecutor.executeScript("return typeof(errorResult) != 'undefined';");
        String getErrorResultValueScript = "if(typeof(errorResult) != 'undefined')"
                + "{return errorResult; }";

        // Inject eventhandler code, if it has not been injected before
        if (!isAlreadyInjectedInPage) {
            String errorCheckingJS = "errorResult = {};"
                    + "window.onerror = function() {"
                    + "errorResult = arguments;"
                    + "};";

            jsExecutor.executeScript(errorCheckingJS);
        }

        // Check of an js error exists
        errorResult = jsExecutor.executeScript(getErrorResultValueScript);

        // If there was an error the result type is ArrayList -> throw an exception
        // Can be made more informative by using the content of the list
        if (errorResult instanceof ArrayList<?>) {
            throw new RuntimeException("A javascript error exists on the page: " + errorResult);
        }
    }
}
