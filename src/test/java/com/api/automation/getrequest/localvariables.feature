Feature: Title Creating local and feature level variables

  Background: Featue level variables Accessible to all sceanrio in this feature
    * def fea_var1 = 234
    * def fea_var2 = 'Automation'

  Scenario: Local Variable Creation
    Given def var1 = 10
    And def var2 = 'Testing'
    * def var3 = var1 + 20
    And print 'printing local variable 1 = ', var1
    And print 'printing local variable 2 = ', var2
    And print 'printing local variable 3 = ', var3
    And print 'printing feature level variable = ', fea_var1

  Scenario: Verify the difference between local & feature variable
    #And print 'printing local variable 1 in another sceanrio = ', var1
    #And print 'printing local variable 2 in another sceanrio = ', var2
    And print 'printing feature level variable = ', fea_var2
