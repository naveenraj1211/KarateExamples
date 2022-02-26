Feature: Title of your feature
  I want to use java script function in feature file

  @smoke
  Scenario: Defining function in single line
    Given def var = function(){ return 'API Automation'}
    * def string = call var
    And print 'printing value to console = ', string

  Scenario: Defining function in more than one line
    Given def randomNumber =
      """
      function(){
      // Math.random() used with Math.floor() can be used to return random integers
      // Math.random() always returns a number lower than 1
      // Returns a random integer from 1 to 100:      
      return Math.floor(Math.random() * 100) + 1;
      }
      """
    * def num = call randomNumber
    And print 'printing value to console = ', num
