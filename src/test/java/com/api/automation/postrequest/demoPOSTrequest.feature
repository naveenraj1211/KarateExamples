Feature: Title of your feature First POST request

  Background: Test Data
    Given url 'https://reqres.in'

  @smoke
  Scenario: Verify user is able to create account
    Given path 'api/users'
    And header Content-Type = 'application/json'
    And request
      """
      {
      "name": "viratkohli",
      "job": "cricket",
      "age": 30
      }
      """
    When method post
    And status 201
    And print response
    And match response.name == 'viratkohli'
