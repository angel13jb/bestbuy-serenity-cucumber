Feature: End to End  Testing for product

  Scenario: Creat a new product
    When User send a post request for creating data
    And  User add name ,type ,price ,shipping ,upc ,description ,manufacture ,model ,url ,image
    Then User should get http responce code 201
    And  User should get the id of the  created new product

  Scenario: Get a created product by ID
    When User send a get request for the created id
    Then User should get response code 200
    And  Verify the name

  Scenario: Update product Detail ID
    When User send a put request for updating product detail
    And  User update name  ,type ,price ,shipping ,upc ,description ,manufacture ,model ,url ,image
    Then User should get http responce code 200 for updating data

    Scenario: Delet product by ID
      When User send delet request for deleting id
      Then User should get response code 204 for deleting data

