Feature: Learn How to use JSON Path Expression for Response validations

  Scenario: Verify response fields
    Given url 'https://reqres.in/api/users/'
    When method GET
    Then status 200
    * configure logPrettyResponse = true
    And print karate.pretty(response)
    * def totalUsers = karate.jsonPath(response,"$.total")
    * print 'Total Users = ', totalUsers
    * def userId = karate.jsonPath(response,"$.data[?(@.first_name == 'Emma')].id")
    * print "User id of Emma = ", userId
    * def first_name = 'George'
    * def userId = karate.jsonPath(response,"$.data[?(@.first_name == \'"+ first_name+"\')].id")
    * print "User id of George = ", userId
    * def users = response.data
    * print  karate.pretty(users)
    * def fun = function(user) { return user.first_name = 'Janet'}
    * def temp = karate.filter(users,fun)
    * print  karate.pretty(temp)
