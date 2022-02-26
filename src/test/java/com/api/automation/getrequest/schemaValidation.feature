Feature: Fuzzy Matching

  Scenario: Ignore or Validate using regular-expressions
    Given def json =
      """
      {
        "ATM": {
            "operations": [
                {
                    "cardvalid": "yes",
                    "mainmenu": [],
                    "result1": [
                        {
                            "balancecheck": "Y",
                            "withdraw": "Y"
                        },
                        {
                            "balancecheck": "Y",
                            "withdraw": "Y"
                        }
                    ]
                },
                {
                    "cardvalid": "NO",
                    "mainmenu": [],
                    "result3": [
                        {
                            "option": "retry",
                            "isValid": "Y",
                            "mainmenu": []
                        },
                        {
                            "option": "reset",
                            "isValid": "Y",
                            "mainmenu": [
                                {
                                    "title": "Bank Account No",
                                    "alertMessage": "Please enter bank account nu."
                                },
                                {
                                    "title": "Mobile No",
                                    "alertMessage": "Please enter mobile no."
                                }
                            ]
                        }
                    ]
                },
                {
                    "cardvalid": "yes",
                    "mainmenu": [
                        {
                            "title": "PIN Generate",
                            "alertMessage": "Green OTP"
                        }
                    ]
                },
                {
                    "cardvalid": "notregistered",
                    "mainmenu": []
                }
            ]
        }
      }
      """
    And def mainmenu = {title:'#string', alertMessage:'#string' }
    And def result1 = {balancecheck:'#string', withdraw:'#string' }
    And def result3 = { option: '#string' ,isValid: '#string', mainmenu: '##[]'  }
    And def operations =
      """
      {
        cardvalid: '#string',
        mainmenu: '##[]',
        result1: '##[] result1',
        result3: '##[] result3'
      
      }
      """
    And def ValidJson = { ATM: { operations:'#[] operations' } }
    And print ValidJson
    And match json == ValidJson
