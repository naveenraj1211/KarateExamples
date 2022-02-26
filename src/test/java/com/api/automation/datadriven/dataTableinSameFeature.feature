Feature: Title of Feature is Scenario Outline Example

  Background: Backgroud Data
    * print "********** API Automation Using Karate Framework ***********"
    * configure logPrettyResponse = true

  Scenario Outline: <Iteration> Verify Data Table in Same Feature
    Given print '<Name>'
    When print '<Domain>'
    Then print '<Tool>'

    Examples: 
      | Name    | Domain | Tool     | Iteration   |
      | Naveen  | Test   | Selenium | Iteration 1 |
      | Rahul   | Dev    | Java     | Iteration 2 |
      | Prakash | Test   | Psotman  | Iteration 3 |

  Scenario Outline: Scenario <scenario> :- Negative Test Login Feature
    Given url 'https://conduit.productionready.io/api/users/login'
    And request
      """
          {
              "user": {
              "email": "<email>",
              "password": "<password>"
              }
          }   
      """
    When method Post
    Then match karate.range(400,499) contains responseStatus
    And match response == <errormessage>

    Examples: 
      | email              | password | errormessage                                    | scenario         |
      | testemail@test.com | test123  | {"errors":{"email or password":["is invalid"]}} | 1 invalid creds  |
      |                    | test123  | {"errors":{"email":["can't be blank"]}}         | 2 empty email    |
      | testemail@test.com |          | {"errors":{"password":["can't be blank"]}}      | 3 empty password |
