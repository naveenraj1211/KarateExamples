Feature: Validating HTTP $ JSON

  Scenario: Verify match == and match != (not equals)
    Given url 'https://randomuser.me/api/'
    When method GET
    Then status 200
    #match ==
    And match $.info.page == 1
    #match != and Validate every element in a JSON array
    And match each $.results..age != 0

  Scenario: verify match contains and (not) !contains
    * def emp = { age: 25, name: 'naveen', gender: 'male' }
    And match emp contains { name: 'naveen'}
    #if one value present assertion will pass
    And match emp !contains { gender: 'female', name: 'naveenr'}

  Scenario: verify match match contains only, contains any and contains deep
    * def cyc =
      """
      {
      "brand": ["Hero","Hercules","Atlas"],
      "bicycle": [
        {
            "color": "red",
            "price": 19.95
        },
        {
            "color": "black",
            "price": 15.20
        }
      ]
      }
      """
    And match cyc.brand contains only ["Atlas","Hercules","Hero"]
    And match cyc.bicycle[*].color contains any ["red","pink","blue"]
    And match cyc..* contains deep  {"price": 15.20}
