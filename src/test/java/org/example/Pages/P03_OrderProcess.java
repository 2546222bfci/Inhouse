package org.example.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class P03_OrderProcess {

        private final WebDriver driver;
        private final Wait<WebDriver> wait;

    public P03_OrderProcess(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
    }
        public void navigateToHomePage() {
            driver.get("https://staging.inhouse.sa/");
        }
        public void searchForProduct(String productName) {
            WebElement searchInput = driver.findElement(By.name("q"));
            searchInput.sendKeys(productName);
            searchInput.submit();

            // Wait for search results to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-item")));
        }

    public void openProduct() throws InterruptedException {
        Thread.sleep(3000); // Consider replacing with WebDriverWait
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product-photo photo product-item-photo product-list-image' and @href='https://staging.inhouse.sa/ar/modern-simple-reading-husk-chair-rhc01012.html' and @tabindex='-1']")));

        // Scroll to the element's location
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

        // Add additional wait time after scrolling if necessary
        Thread.sleep(1000); // Adjust timing as needed

        // Wait for the element to be clickable again after scrolling
        wait.until(ExpectedConditions.elementToBeClickable(product)).click();
    }

    public void addToCartFromProductPage() {
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='action primary tocart' and  @title='أضف لسلة التسوق' and @id='product-addtocart-button']")));
            addToCartButton.submit();
        }
    public void openCartPoPuP() {
        try {
            Thread.sleep(3000); // Adjust the sleep duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement openPoP = driver.findElement(By.xpath("//a[@class='action showcart' and @href='https://staging.inhouse.sa/ar/checkout/cart/']"));
        openPoP.click();
    }
    public void proceedToCheckout() {
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout' and @type='button' and @class='action primary checkout' and @data-action='close']")));
            checkoutButton.click();
        }
    //div[@class='step-title' and @data-role='title' and text()='Shipping Address']
    public void shippingFormTitle(){
        try {
            Thread.sleep(3000); // Adjust the sleep duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement formTitle = driver.findElement(By.xpath(" //div[@class='step-title' and @data-role='title' and text()='عنوان الشحن   ']"));
        String expectedTitle="عنوان الشحن";
        String actualTitle=formTitle.getText();
        Assert.assertEquals(actualTitle,expectedTitle);
        System.out.println(actualTitle);
    }

    public void enterShippingAddressEmailPass(String email) {

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='customer-email']\n")));
            emailInput.sendKeys(email);
        }
    public void selectTheRegion(String region) {

        WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='select' and @name='region_id']")));        Select regionSelect = new Select(regionDropdown);
         //regionSelect.selectByVisibleText(region);
         regionSelect.selectByValue("659");
        //regionSelect.selectByVisibleText(region);
    }
    public void selectTheCity(String city) {

        try {
            Thread.sleep(3000); // Adjust the sleep duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class='select city-select']\n")));
        Select citySelect = new Select(cityDropdown);
        citySelect.selectByValue(city);
    }
    public void shippingInfo(String fName,String lName,String neighboord,String streetLine,String phoneNum) {

        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='input-text' and @type='text'  and @name='firstname'  and @placeholder='الإسم الأول *']")));
        firstNameInput.sendKeys(fName);

        WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='input-text' and @type='text'  and @name='lastname'  and @placeholder='اسم العائلة *']")));
        lastNameInput.sendKeys(lName);

        WebElement neighboordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='input-text' and @type='text'  and @name='company'  and @placeholder='الحي *']")));
        neighboordInput.sendKeys(neighboord);

        WebElement streetLineInput =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='input-text' and @type='text'  and @name='street[0]'  and @placeholder='عنوان الشارع: Line 1 *']")));
        streetLineInput.sendKeys(streetLine);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='admin__control-text mobile-number sms-mobile-number'  and @type='text'  and @name='telephone'  and @placeholder='رقم الهاتف *']")));
        phoneInput.sendKeys(phoneNum);
    }

    public void clickOnNextButton() {

           //  nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button action continue primary' and @data-role='opc-continue' and @type='submit']")));
        WebElement nextButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button action continue primary' and @data-role='opc-continue' and @type='submit']")));
            // Try clicking using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
            nextButton.submit();
    }

    /*
    And  Select the payment method "cash on delivery"
    public void selectPaymentMethod(String method) {

        }*/
    //button[contains(@class, 'checkout tabby tabby_installments') and contains(@class, 'action primary') and @type='submit']
//button[@class='action primary checkout tabby tabby_installments' and @type='submit']
    public void tabbyPlaceOrder() {
        try {

            WebElement placeOrderByTabby = driver.findElement(By.xpath("//button[@class='action primary checkout tabby tabby_installments' and @type='submit']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderByTabby);
             placeOrderByTabby = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='action primary checkout tabby tabby_installments' and @type='submit']")));

            placeOrderByTabby.click();


            // Wait for the next page to load (increase wait time)
            WebDriverWait nextPageWait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased wait time
            nextPageWait.until(ExpectedConditions.urlContains("https://checkout.tabby.ai/auth?sessionId=bce6093e-8e19-46a4-a711-97513e13c687&apiKey=pk_54a7509e-fa38-4d61-a6ef-7abc97540cbe&product=installments&merchantCode=main_website_store_SAR&fl=1"));

            // Verify that the title of the next page is "Tabby Checkout"
            String actualTitle = driver.getTitle();
            String expectedTitle = "Tabby Checkout";
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("Actual title: " + actualTitle);
        } catch (TimeoutException e) {
            // Handle timeout exception
            System.out.println("Timeout occurred while waiting for the next page to load: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("Error occurred during Tabby place order: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public String getConfirmationMessage() {
           // WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("confirmation-message")));
          //  return confirmationMessage.getText();
            return null;
    }

    public void addToCartFromProductList() {

            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='action tocart primary' and  @title='أضف لسلة التسوق' ] ")));
            addToCartButton.submit();
    }

    //input[@type='radio' and @name='payment[method]' and @class='radio' and @value='tabby_installments']
    public void selectTabby(){
        WebElement tabbyLocator=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @name='payment[method]' and @class='radio' and @value='tabby_installments']")));
        tabbyLocator.click();
    }
    public void selectMada() {
        try {
            // Wait for the validation message to disappear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'validation-message') and contains(text(), 'Carrier with such method not found')]")));

            // Once the validation message disappears, select the Mada payment method
            WebElement madaLocator = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and @name='payment[method]' and @class='radio' and @value='mada']")));
            madaLocator.click();
            System.out.println("Mada payment method selected.");
        } catch (TimeoutException e) {
            // Log any timeout exceptions
            System.out.println("Timeout occurred while waiting for validation message to disappear or for Mada payment method to be clickable: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Log any other exceptions
            System.out.println("Error occurred while selecting Mada payment method: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void selectCashOnDelivery() {
        WebElement cashOnDeliveryLocator = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@type='radio' and @name='payment[method]' and @class='radio' and @value='cashondelivery']")));

        cashOnDeliveryLocator.click();
    }

    public void placeOrderofCashOnDelivery(){
        WebElement cashonPlaceOrderBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='action primary checkout' and @type='submit' and @title='إنشاء الطلب']")));
        cashonPlaceOrderBtn.submit();
    }

    public void InputetheNewQuantity() throws InterruptedException {
        WebElement quatityInput=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='number' and @class='item-qty cart-item-qty']")));
        quatityInput.sendKeys(Keys.CONTROL+"a");
        quatityInput.sendKeys(Keys.DELETE);
        quatityInput.sendKeys("100");
        Thread.sleep(3000);
    }
    public void UpdateCartBtn() throws InterruptedException {
        WebElement updateBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='update-cart-item' and @title='تحديث']")));
        updateBtn.click();
        Thread.sleep(3000);
    }

    public void ViewCart() throws InterruptedException {
        Thread.sleep(3000);
        WebElement viewCartBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action btn viewcart' and @href='https://staging.inhouse.sa/ar/checkout/cart/']")));
        viewCartBtn.click();
    }

    public void verifyProductQuantity(String product, int expectedQuantity) {
        try {
            // Wait for the quantity element to be visible
            WebElement quantityElement;
            quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//strong[@class='product-item-name'][a[text()='" + product + "']]/ancestor::td[@class='col item']/following-sibling::td[@class='col qty box-tocart']//input[@title='الكميّة']")
            ));

            // Get the text of the quantity element and trim any leading or trailing whitespace
            String actualQuantityString = quantityElement.getAttribute("value").trim();

            // Output the actual quantity string for debugging
            System.out.println("Actual Quantity String: " + actualQuantityString);

            // Convert the actual quantity string to an integer
            int actualQuantity = Integer.parseInt(actualQuantityString);

            // Assert the actual quantity matches the expected quantity
            Assert.assertEquals(expectedQuantity, actualQuantity, "Product quantity does not match");

            // Log information for debugging
            System.out.println("Product Name: " + product);
            System.out.println("Actual Quantity: " + actualQuantity + ", Expected Quantity: " + expectedQuantity);
        } catch (NumberFormatException e) {
            // Handle the case where actual quantity string cannot be parsed to an integer
            System.out.println("Failed to parse actual quantity string to an integer: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public void openAcategory(){
        WebElement categoryMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='navigation verticalmenu side-verticalmenu']")));
        categoryMenu.click();

        WebElement furnitureCategory=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='level-top' and contains(text(),'الأثاث')]\n")));
        furnitureCategory.click();
    }

    public void selectReclinerType() {
     try {
        WebElement redColorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='am-swatch-wrapper item swatch-option-link-layered']//div[@class='swatch-option color' and @option-label='أحمر' and @style='background-color: #ff0000;']")));

        // Scroll element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", redColorOption);

        // Adjust z-index or other styles if necessary
        // Example: ((JavascriptExecutor) driver).executeScript("arguments[0].style.zIndex = '9999';", redColorOption);

        // Move the mouse away if there's a hover effect
        Actions actions = new Actions(driver);
        actions.moveToElement(redColorOption, -50, -50).perform();

        // Click the element using JavaScriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", redColorOption);
    } catch (NoSuchElementException e) {
        System.out.println("Red color option element not found: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An error occurred while selecting the red color option: " + e.getMessage());
    }
}
    public void addTocart() {
        WebElement addTocart_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@data-role='tocart-form'][@data-product-sku='906193']")));

        int attempts = 0;
        while(attempts < 3) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", addTocart_btn);

                addTocart_btn.click();
                break;
            } catch(org.openqa.selenium.WebDriverException e) {
                // Retry clicking if WebDriverException occurs
                attempts++;
                System.out.println("Retry attempt: " + attempts);
            }
        }
    }


    public void validationMessageDisplay()
    {

        String actualPageTitle=driver.getTitle();
        String expectedTitle="أثاث فاخر وعصري بتصميمات مميزة وجودة عالية | إن هاوس";
        Assert.assertEquals(actualPageTitle,expectedTitle);
        System.out.println("actualPageTitle : "+actualPageTitle);

        WebElement validationMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]\n")));
        boolean isDispalyed=validationMessage.isDisplayed();
        Assert.assertTrue(isDispalyed,"yes validation mssage is displayed");

        String expectedMessage="تحتاج إلى تحديد خيارات هذا المنتج.";
        String actualResult=validationMessage.getText();

        Assert.assertEquals(actualResult,expectedMessage);
        System.out.println("actualResult'validation message': is : "+actualResult);
    }


    public void selectAttributes() {
        //select classic
        WebElement selectedRecyliner = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='swatch-option text' and @data-option-label='ثابت']")));

        WebElement selectColor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='swatch-option image' and @data-option-label='أزرق داكن']")));

        int attempts = 0;
        while (attempts < 3) {
            try {
                // Scroll elements into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedRecyliner);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectColor);

                // Click the elements
                selectedRecyliner.click();

               // selectColor.click();
                selectColor.sendKeys(Keys.RETURN);
                break;
            } catch (org.openqa.selenium.WebDriverException e) {
                // Retry clicking if WebDriverException occurs
                attempts++;
                System.out.println("Retry attempt: " + attempts);
            }
        }
    }

    public void addToCartProductWithAttributes(){
        WebElement addTocart=wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("//button[@id='product-addtocart-button' and @type='submit' and @title='أضف لسلة التسوق' and contains(@class, 'action primary tocart')]\n")));
        addTocart.submit();
    }

    public void productInTheCart(String expectedProductName) {
        // Wait for the product name element to be visible
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-bind=\"attr: {href: product_url}, html: $parent.getProductNameUnsanitizedHtml(product_name)\"]")));

        // Get the actual product name from the element
        String actualProductName = productName.getText();

        // Assert that the actual product name matches the expected product name
        Assert.assertEquals(actualProductName, expectedProductName);

        // Print the actual product name
        System.out.println("Actual product name: " + actualProductName);
    }

    public void checkPriceClassic(String expectedPrice){
        WebElement pricevalue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-price-amount='959']//span[@class='price']")));
        String actualPrice=pricevalue.getText();
        Assert.assertEquals(actualPrice,expectedPrice);
        System.out.println("Actual Price: "+actualPrice);
    }

    public void selectRocking() {
        WebElement rocking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='swatch-option text' and @data-option-label='هزاز']")));

        int attempts = 0;
        while (attempts < 3) {
            try {
                // Scroll elements into view

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rocking);

                rocking.click();
                break;
            } catch (org.openqa.selenium.WebDriverException e) {
                // Retry clicking if WebDriverException occurs
                attempts++;
                System.out.println("Retry attempt: " + attempts);
            }
        }
        }

        public void changePrice(String expectedPrice){

           WebElement newPrice=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-price-amount='959']//span[@class='price']")));

           String actualPrice=newPrice.getText();
           Assert.assertEquals(actualPrice,expectedPrice);

           System.out.println("actual Price after changed : "+actualPrice);
    }


    public void openProducts() throws InterruptedException {
        Thread.sleep(3000); // Consider replacing with WebDriverWait
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
                .xpath("//a[@class='product-photo photo product-item-photo product-list-image']")));

        // Start from the second product (index 1)
        for (int i = 1; i < products.size(); i++) {
            // Get the product at index i
            WebElement product = products.get(i);

            // Scroll to the element's location
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

            // Add additional wait time after scrolling if necessary
            Thread.sleep(1000); // Adjust timing as needed

            // Wait for the element to be clickable again after scrolling
            product.click();

            addToCartProductWithAttributes();
            // Wait for the product to be added to the cart
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='action showcart']")));

            // If it's not the last product, navigate back to the product list page
            if (i != products.size() - 1) {
                driver.navigate().back();
                // Wait for the product list page to load after navigating back
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product-photo photo product-item-photo product-list-image']")));
                // Refresh the products list
                products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='product-photo photo product-item-photo product-list-image']")));
            }
        }
    }

    public void verifyTappyCheckoutPageTitle(){
        String actualPageTitle=driver.getTitle();
        String expectedPageTitle="Tabby Checkout";
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        System.out.println(actualPageTitle);
        System.out.println(expectedPageTitle);


    }

    public void verifyMadaCheckoutPageTitle(){
        String actualPageTitle=driver.getTitle();
        String expectedPageTitle="In House | دفع";
        Assert.assertEquals(actualPageTitle,expectedPageTitle);
        System.out.println(actualPageTitle);
        System.out.println(expectedPageTitle);


    }


}



