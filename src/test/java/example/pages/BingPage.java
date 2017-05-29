package example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * The Bing page object.
 * In order to keep this example simple, this page object contains both the search logic and the result list logic.
 **/
public class BingPage {

    /**
     * The search field in which the search text can be entered.
     **/
    @FindBy(id = "sb_form_q")
    private WebElement searchField;

    /**
     * The button, which triggers the search process.
     **/
    @FindBy(id = "sb_form_go")
    private WebElement searchButton;

    /**
     * Includes the bing result list module in this page.
     * The given locator can be used in the custom webelement.
     **/
    @FindBy(id = "b_results")
    private WebElement resultListContentElement;

    /**
     * Searches for a text.
     *
     * @param searchText The text for which will be searched.
     **/
    public void search(String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
        searchButton.click();
    }

    /**
     * Opens a search result by clicking on the result link.
     *
     * @param searchResultNumber The number of the search result, which should be opened. 0 is the first search result.
     **/
    public void openSearchResult(int searchResultNumber) {
        List<WebElement> resultLinks = resultListContentElement.findElements(By.xpath(".//li//h2//a"));

        resultLinks.get(searchResultNumber).click();
    }
}
