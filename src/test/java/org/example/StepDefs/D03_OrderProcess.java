package org.example.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Pages.P03_OrderProcess;

import static org.example.StepDefs.Hooks.driver;

public class D03_OrderProcess {
    P03_OrderProcess p03_OrderProcess=new P03_OrderProcess(driver);
    @Given("Navigate the website home page")
    public void NavigateTheHomePage(){
        p03_OrderProcess.navigateToHomePage();
    }

    @When("search for {string}")
    public void searchFor(String productName) {
        p03_OrderProcess.searchForProduct(productName);
    }

    @And("open the Modern Simple Reading Husk Chair chair")
    public void openTheChair() {
        try {
            p03_OrderProcess.openProduct();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("add the chair to my shopping cart")
    public void addTheChairToMyShoppingCart() {

        p03_OrderProcess.addToCartFromProductPage();
    }
    @And("click on the icon cart")
    public void clickOnTheIconCart() {
        p03_OrderProcess.openCartPoPuP();
    }
    @And("proceed to checkout")
    public void proceedToCheckout() {
        p03_OrderProcess.proceedToCheckout();
    }
    /*
    //Then the customer be in shipping page to fill the shipping address
    @Then("the customer be in shipping page to fill the shipping address")
    public void theCustomerBeInShippingPageToFillTheShippingAddress() {

        p03_OrderProcess.shippingFormTitle();
    }*/


    @When("Enter Email {string}")
    public void enterEmailAndPassword(String email) {
        p03_OrderProcess.enterShippingAddressEmailPass(email);
    }

    @And("select the region {string}")
    public void selectTheRegion(String region) {
        p03_OrderProcess.selectTheRegion(region);
    }

    @And("select the city {string}")
    public void selectTheCity(String city) {
        p03_OrderProcess.selectTheCity(city);
    }

    @And("Enter first name {string} and last name {string} and neighboord {string} and street line {string} and phone {string}")
    public void enterFirstNameAndAndNeighboordAndStreetLine(String FN, String LN, String Ne, String SL, String PN) {
         p03_OrderProcess.shippingInfo(FN, LN, Ne, SL, PN);
    }

    @And("Click on Next Button")
    public void clickOnNextButton() {
        p03_OrderProcess.clickOnNextButton();
    }

    /*@And("Select the payment method {string}")
    public void selectThePaymentMethod(String arg0) {
    }*/
    @And("Select tabby")
    public void selectTabby() {
        p03_OrderProcess.selectTabby();

    }
    @And("click on place order button")
    public void clickOnPlaceOrderButton() {
        p03_OrderProcess.tabbyPlaceOrder();

    }

    @Then("Find a confirmation message for my order")
    public void findAConfirmationMessageForMyOrder() {

    }

    @And("add the {string} to my shopping cart")
    public void addTheToMyShoppingCart(String arg0) {
        p03_OrderProcess.addToCartFromProductList();
    }

    @And("select cash on delivery method")
    public void selectCashOnDeliveryMethod() {
        p03_OrderProcess.selectCashOnDelivery();
    }

    @And("click on its place order button")
    public void clickOnItsPlaceOrderButton() {
        p03_OrderProcess.placeOrderofCashOnDelivery();
    }

    @And("update the quantity field at the pop-up")
    public void UpdateQuantity() {
        try {
            p03_OrderProcess.InputetheNewQuantity();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("click on the update button")
    public void clickOnTheButton() {
        try {
            p03_OrderProcess.UpdateCartBtn();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("view the cart")
    public void viewTheCart() {

        try {
            p03_OrderProcess.ViewCart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the quantity of {string} in the cart should be {int}")
    public void theQuantityOfInTheCartShouldBe(String productName, int Qty) {

            p03_OrderProcess.verifyProductQuantity(productName,Qty);
    }


    @Given("navigate any category to be in the product list page")
    public void navigateAnyCategoryToBeInTheProductListPage() {
        p03_OrderProcess.openAcategory();
    }

    @When("apply a filter for Recliner Type from the side navigation menu")
    public void applyAFilterForReclinerTypeFromTheSideNavigationMenu() {
         p03_OrderProcess.selectReclinerType();
    }

    @When("adding أمريكان بولو | كرسي استرخاء ، مخمل to the cart")
    public void addingToTheCart() {
           p03_OrderProcess.addTocart();
    }

    @Then("product page is being open and validation message should dispaly")
    public void validationMessageShouldDispaly() {

        p03_OrderProcess.validationMessageDisplay();

    }


    @When("Select the Required attributes")
    public void selectTheRequiredAttributes() {
        p03_OrderProcess.selectAttributes();
    }

    @And("click on add to cart")
    public void clickOnAddToCart() {
        p03_OrderProcess.addToCartProductWithAttributes();
    }

    @Then("product {string} is being at the cart and confirmation message display")
    public void productBeInTheCart(String  productName) {
        p03_OrderProcess.productInTheCart(productName);
    }

    @When("Select classic as recycliner and the color")
    public void selectClassicAsRecyclinerAndTheColor() {
        p03_OrderProcess.selectAttributes();
    }


    @Then("find the price is {string}")
    public void findThePriceIs(String arg0) {
        p03_OrderProcess.checkPriceClassic(arg0);
    }

    @And("change the recycliner from classic to rocking")
    public void changeTheRecyclinerFromClassicToRocking() {
        p03_OrderProcess.selectRocking();
    }

    @Then("the price must changed to {string}")
    public void thePriceMustChangedTo(String arg0) {
        p03_OrderProcess.changePrice(arg0);
    }

    @Then("make sure that the أمريكان بولو | كرسي استرخاء ، مخمل is added with its new price")
    public void makeSureThatTheProductIsAddedWithItsNewPrice() {
    }
}
