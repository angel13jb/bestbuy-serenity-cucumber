@category
Feature: End To end Testing For Category

  Scenario: Creating category
    When User insert  name and id
    And  user send a Post request for creating category
    Then user should be able to creat category successfully

  Scenario: Getting category by given id
    When User send a get request by given id
    Then User should be able to get category
    And verify name of category

  Scenario: Updating category
    When User update category by give id with patch request
    Then User should be able to update succefully

  Scenario: Deleting category
    When user send delet request for deleting category
    Then user should be able to delete category successfully
    And  verify category is deleted
