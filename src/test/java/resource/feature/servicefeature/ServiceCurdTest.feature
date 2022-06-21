@smoke
Feature: End To End Testing for services

  Scenario: Creat searvice by post Request
    When User send a post request for creating Serivice
    And  User  add in name field
    Then User should be able to creat new searvice

  Scenario: Get searvice by created Id
    When User send a get request by created new  searvice Id
    Then user should be able to get response of new created searvice
    And verify name

  Scenario: Update searvice by created id
    When User Update created searvice
    And  user update name
    Then  User  should get success response

  Scenario: Delete service by Id
    When User send delet request for deleting searvice
    And  User should get success deleted response
    And  Verify searvice is deleted
