
@tag
Feature: Purchage product from Ecommerce Website 
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce website page 

  @tag2
  Scenario Outline: Positiev testof Submitting the Product Order
    Given Logged In with Valid Username <name> and Password <password>
    When I add product <productName>to cart 
    And Checkout <productName> and submit order
    Then Verify the displayed  "THANKYOU FOR THE ORDER." confirmation message

    Examples: 
      | name  								| password 				| productName|
      |kks96.narola@gmail.com | Kuldeep@1996		| ZARA COAT 3|
     
