@store
Feature: End to End Store testing

  Scenario: Creating store data
    When user send a post request for creating store data
    And User insert name, type, address, address2, city, state,zip, lat, lng, hours
    Then User should be able to creat store successfully
    And User should get the store Id


  Scenario: Geting single store by given Id
    When user Send a request for single created store
    Then user should be able to get Store detail succesfully
    And  verify name to check store has been created

    Scenario: Updating store by given ID
      When user send a patch request for update store
      And User update name
      Then user should be able to add successfully

      Scenario: Deleting store by given id
        When User send delete request for deleting store
        And  User should be able to delete store succefully
        And verify store has been deleted