Feature: Title of Feature

  @test @ignore
  Scenario: Title of Scenario
    * def scenario = 'Negative Test Login Feature'
    * def email = 'testemail@test.com'
    * def password = 'test123'
    * def errormessage =
      """
      {
      "errors": {
      "email or password": ["is invalid"]
      	}
      }
      """

  @smoke @ignore
  Scenario: Title of Scenario
    * def username = uname
    * def email = email
    * def password = username + email
    * print  username, email, password
