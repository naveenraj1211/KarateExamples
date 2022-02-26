Feature: Title of Feature Calling One Sceanrio From Another Sceanrio

  Background: Test Data
    * print  '********** API Automation Using Karate Framework ***********'

  @test1 @ignore
  Scenario: Base Test
    Given def cn = 'India'
    And print 'Test 1 Country Name: ', cn

  @test2
  Scenario: Verify calling one Sceanrio in another in same feature
    * def result = call read('callingSceanrioInSameFeature.feature@test1')
    * def country = result.cn
    Given def cap = 'New Delhi'
    And print 'Test 2 Country Name: ', country
    And print 'Capital City: ', cap
