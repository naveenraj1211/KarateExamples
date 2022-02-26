Feature: Test Title Reading Test Data Form JSON File

  Scenario: Postive Test Read from file functionality
    Given url 'https://reqres.in/api/users/1'
    # creating variable to store the value from file
    * def excpectedResponse = read('../readingTestDataformJSON.json')[0]
    When method GET
    Then status 200
    And print excpectedResponse
    And match response == excpectedResponse

  Scenario: Negative Test Read from file functionality
    Given url 'https://reqres.in/api/users/1'
    # creating variable to store the value from file
    * def excpectedResponse = read('../readingTestDataformJSON.json')[1]
    When method GET
    Then status 200
    And print excpectedResponse
    And match response != excpectedResponse
