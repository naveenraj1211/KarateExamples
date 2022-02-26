Feature: Validating JSON array

  Scenario: Verify JSON Keys, values using PATH
    Given def data =
      """
      {
        "students": {
            "report": [
                {
                    "subject": "science",
                    "name": "rahul",
                    "percentage": 91.1
                },
                {
                    "subject": "hindi",
                    "name": "vivek",
                    "percentage": 82.99
           
                },
                {
                    "subject": "kannada",
                    "name": "manish",
                    "percentage": 98.99,
                    "rank": "S+"
                },
                {
                    "subject": "english",
                    "name": "prakash",
                    "percentage": 93.99,
                    "rank": "A"
                }
            ],
            "strength": {
                "boys": 43,
                "girls": 36
            }
        },
        "gradeA": 90
      }
      """
    And print data
    And match data.students.report[3].subject == 'english'
    And match data.students.report[-2].subject == 'kannada'
    And match data.students.report[*].name contains ['manish','rahul']
    And match data..boys == [43]
		And match data..report[?(@.rank)].name contains only ['manish','prakash']
		And match data..report[?(@.percentage <= $['gradeA'])].name == ['vivek']
		And print data.students.report.length