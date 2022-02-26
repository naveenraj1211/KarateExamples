Feature: Title of your feature
  Embedded expressions will be super-useful for re-use and data-driven tests

  Background: Test Data
    Given url 'https://reqres.in'

  @smoke
  Scenario Outline: Verify user is able to create account
    Given path 'api/users'
    And header Content-Type = 'application/json'
    And request
      """
      {
      "name": "#(username)",
      "job": "#(Job)",
      "age": 30
      }
      """
    When method post
    And status 201
    And print response
    And match response.name == '#(username)'

    Examples: 
      | username | Job                  |
      | selenium | WebAutomationTool    |
      | Karate   | APIAutomationTool    |
      | Appium   | MobileAutomationTool |
