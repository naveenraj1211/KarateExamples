Feature: Title of your feature
  I want to use query parameter is my requets

  @regression @ignore
  Scenario: Title of your scenario two query parameters
    Given url 'https://reqres.in'
    And path '/api/users'
    #And param page = 1
    #And param per_page = 1
    And params { page: 1, per_page: 1}
    When method GET
    Then status 200
    And print response
    And match response.page == 1
    And match response.per_page == 1

  @critical
  Scenario: Title of your scenario outline String query parameter
    Given url 'https://jsonplaceholder.typicode.com'
    And path '/comments'
    And param postId = 2
    And param name = 'provident id voluptas'
    #And params { page: 1, per_page: 1}
    When method GET
    Then status 200
    And print response
    And match each response[*].postId == 2
