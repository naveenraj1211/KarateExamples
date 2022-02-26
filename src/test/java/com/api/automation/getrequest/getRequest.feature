Feature: Testing the fetch API of the Application under test

  Scenario: Verify Get Articles API
    Given url 'https://api.realworld.io/api/articles'
    And header Authorization = "Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im5hdmVlbnRlc3QxMjNAZ2FtaWwuY29tIiwidXNlcm5hbWUiOiJuYXZlZW50ZXN0MTIzIiwiYmlvIjpudWxsLCJpbWFnZSI6Imh0dHBzOi8vcmVhbHdvcmxkLXRlbXAtYXBpLmhlcm9rdWFwcC5jb20vaW1hZ2VzL3NtaWxleS1jeXJ1cy5qcGVnIiwiaWF0IjoxNjM0ODg5NzA2LCJleHAiOjE2NDAwNzM3MDZ9.aP7jmZByOm_2EuzncrdxrTyegbjf9065pAU2Ilw_oNY"
    When method GET
    Then status 200
