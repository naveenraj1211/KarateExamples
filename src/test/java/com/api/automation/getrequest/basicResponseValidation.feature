Feature: Testing HTTP response

  Scenario: Verify response body validations
    Given url 'https://api.realworld.io/api/articles'
    And header Authorization = "Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im5hdmVlbnRlc3QxMjNAZ2FtaWwuY29tIiwidXNlcm5hbWUiOiJuYXZlZW50ZXN0MTIzIiwiYmlvIjpudWxsLCJpbWFnZSI6Imh0dHBzOi8vcmVhbHdvcmxkLXRlbXAtYXBpLmhlcm9rdWFwcC5jb20vaW1hZ2VzL3NtaWxleS1jeXJ1cy5qcGVnIiwiaWF0IjoxNjM0ODg5NzA2LCJleHAiOjE2NDAwNzM3MDZ9.aP7jmZByOm_2EuzncrdxrTyegbjf9065pAU2Ilw_oNY"
    When method GET
    Then status 203
    #To print the response in console
    And print response
    #To use values in the response headers variable responseHeaders
    And match responseHeaders['Content-Type'][0] == 'application/json; charset=utf-8'
    And match responseHeaders['Connection'][0] == 'keep-alive'
    #Then match header Content-Length == '33'
    # 'contains' works as well
    Then match header Content-Type contains 'application'
    #to check a range of values
    And match karate.range(200,299) contains responseStatus
    #The response time (in milliseconds)
    And assert responseTime < 3000
    # $ represents the response
    And match $.articlesCount == 1
