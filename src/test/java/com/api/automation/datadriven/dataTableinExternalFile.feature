Feature: Title of Feature is Scenario Outline Example

  Background: Backgroud Data
    * print "********** API Automation Using Karate Framework ***********"
    * configure logPrettyResponse = true

  #  * def testData = read('testData.csv')
  Scenario Outline: <Iteration> Verify Data Table in Same Feature
    Given print '<Name>'
    When print '<Domain>'
    Then print '<Tool>'

    Examples: 
      #|testData|
      | read('testData.csv') |
