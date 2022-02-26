Feature: Title of Feature Calling One Sceanrio From Another Feature

  Background: Test Data
    * print  '********** API Automation Using Karate Framework ***********'

  @ignore
  Scenario: Fetching values from another feature
    * def result = call read('testData.feature@test')
    * def scenario = result.scenario
    * def email = result.email
    * def password = result.password
    * def errormessage = result.errormessage
    * print "scenario :", scenario
    * print "email :", email
    * print "password :", password
    * print "errormessage :", errormessage
    Given url 'https://conduit.productionready.io/api/users/login'
    And request
      """
          {
              "user": {
              "email": '#(email)',
              "password": '#(password)'
              }
          }   
      """
    When method Post
    Then status 403
    And match response == '#(errormessage)'

  Scenario: Passing values to another feature
    * def email = 'testemail@test.com'
    * def results = call read('testData.feature@smoke') {uname:'virat', email:'#(email)'}
    * def password = results.password
    * print password
