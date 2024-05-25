@smoke
Feature:Order Process without login

  Scenario Outline:
  Place an order for a single product with tabby payment method
    Given Navigate the website home page
    When  search for "كلاسيك"
    And   add the "كلاسيك بالاس | طقم مفرش شتوي 3 قطع, مجوز, 260×240 سم, رمادي" to my shopping cart
    And   click on the icon cart
    And   proceed to checkout
    When  Enter Email "<email>"
    And   select the region "<region>"
    And   select the city "<city>"
    And   Enter first name "<first_name>" and last name "<last_name>" and neighboord "<neighborhood>" and street line "<street_line>" and phone "<phone>"
    And   Click on Next Button
    And   Select tabby
    And   click on place order button
    Then  redrict to Tabby checkout page

    Examples:
      | email          | region          | city              | first_name | last_name | neighborhood | street_line | phone      |
      | test@gmail.com | المنطقة الشرقية | عين الدار الجديدة | test       | test      | test         | test        | 0123456789 |

  Scenario Outline: Update the quantity of a product in the cart
    Given Navigate the website home page
    When  search for "chair"
    And   open the Modern Simple Reading Husk Chair chair
    And   add the chair to my shopping cart
    And   click on the icon cart
    And   update the quantity field at the pop-up
    And   click on the update button
    And   view the cart
    Then  the quantity of "Modern Simple Reading Husk Chair" in the cart should be 100
    And   click on the icon cart
    And   proceed to checkout
    When  Enter Email "<email>"
    And   select the region "<region>"
    And   select the city "<city>"
    And   Enter first name "<first_name>" and last name "<last_name>" and neighboord "<neighborhood>" and street line "<street_line>" and phone "<phone>"
    And   Click on Next Button
    And   Select mada

    Examples:
      | email          | region          | city              | first_name | last_name | neighborhood | street_line | phone      |
      | test@gmail.com | المنطقة الشرقية | عين الدار الجديدة | test       | test      | test         | test        | 0123456789 |


  Scenario Outline: Place an order for a single product with cash on delivery
    Given Navigate the website home page
    When  search for "كلاسيك"
    And   add the "كلاسيك بالاس | طقم مفرش شتوي 3 قطع, مجوز, 260×240 سم, رمادي" to my shopping cart
    And   click on the icon cart
    And   proceed to checkout
    When  Enter Email "<email>"
    And   select the region "<region>"
    And   select the city "<city>"
    And   Enter first name "<first_name>" and last name "<last_name>" and neighboord "<neighborhood>" and street line "<street_line>" and phone "<phone>"
    And   Click on Next Button
    And   select cash on delivery method
    And   click on its place order button
    Then  Find a confirmation message for my order

    Examples:
      | email          | region          | city              | first_name | last_name | neighborhood | street_line | phone      |
      | test@gmail.com | المنطقة الشرقية | عين الدار الجديدة | test       | test      | test         | test        | 0123456789 |

  Scenario Outline: Place an order for Multiple Items
    Given Navigate the website home page
    When search for "chair"
    And open the Modern Simple Reading Husk Chair chair
    And add the chair to my shopping cart
    And click on the icon cart
    And search for "كلاسيك"
    And add the "كلاسيك بالاس | طقم مفرش شتوي 3 قطع, مجوز, 260×240 سم, رمادي" to my shopping cart
    And add the "<product name>" to my shopping cart
    And click on the icon cart
    And proceed to checkout
    And Enter Email "<email>"
    And select the region "<region>"
    And select the city "<city>"
    And Enter first name "<first_name>" and last name "<last_name>" and neighboord "<neighborhood>" and street line "<street_line>" and phone "<phone>"
    And Click on Next Button
    And Select tabby
    And click on place order button
    Then Find a confirmation message for my order

    Examples:
      | email          | region          | city              | first_name | last_name | neighborhood | street_line | phone      |
      | test@gmail.com | المنطقة الشرقية | عين الدار الجديدة | test       | test      | test         | test        | 0123456789 |

  Scenario: Applying Filter and Verifying Selected Attribute in Product Page
    Given navigate any category to be in the product list page
    When  apply a filter for Recliner Type from the side navigation menu
    Then  the product list should be updated with the filtered products
    And   the selected color attribute should be correctly applied to the products
    When  select a filtered product
    Then  the product page should open
    And   find the selected color attribute applied on the product page

  Scenario:Add product to the cart  with required attributes like color or recyliner type or  others attribute from the product page
    Given navigate any category to be in the product list page
    When  adding أمريكان بولو | كرسي استرخاء ، مخمل to the cart
    Then product page is being open and validation message should dispaly
    When Select the Required attributes
    And  click on add to cart
    And  click on the icon cart
    Then product "أمريكان بولو | كرسي استرخاء ، مخمل" is being at the cart and confirmation message display

  Scenario:place order with attributes to checck
  if the price is changed when changing the recycliner or others that the price dependens on
    Given navigate any category to be in the product list page
    When adding أمريكان بولو | كرسي استرخاء ، مخمل to the cart
    Then product page is being open and validation message should dispaly
    When Select classic as recycliner and the color
    Then find the price is "999.00 ر.س"
    And  change the recycliner from classic to rocking
    Then the price must changed to "1199.00 ر.س"
    And  click on add to cart
    And  click on the icon cart
    Then make sure that the أمريكان بولو | كرسي استرخاء ، مخمل is added with its new price

  Scenario Outline:Place an order for 100 Items
    Given Navigate the website home page
    When  search for "chair"
    And   open all the products at the product list page that is opened and add to the cart
    When Select the Required attributes

    When  search for "غرفة"
    And   open all the products at the product list page that is opened and add to the cart
    When  search for "لباد"
    And   open all the products at the product list page that is opened and add to the cart
    And   click on the icon cart
    And   proceed to checkout
    When  Enter Email "<email>"
    And   select the region "<region>"
    And   select the city "<city>"
    And   Enter first name "<first_name>" and last name "<last_name>" and neighboord "<neighborhood>" and street line "<street_line>" and phone "<phone>"
    And   Click on Next Button
    And   Select mada
    And   click on place order button
    Then  redrict to Mada checkout page

    Examples:
      | email          | region          | city              | first_name | last_name | neighborhood | street_line | phone      |
      | test@gmail.com | المنطقة الشرقية | عين الدار الجديدة | test       | test      | test         | test        | 0590913013 |
