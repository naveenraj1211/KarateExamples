Feature: Testing file upload functionality

  Background: Test Data
    * url 'https://v2.convertapi.com'
    * configure logPrettyResponse = true

  Scenario: Verify File upload
    Given path 'upload'
    And multipart file filename = { read:'testUploadFile.txt', filename:'testUploadFile.txt', Content-type:'multipart/form-data' }
    When method POST
    And status 200
    And match response.FileName =='testUploadFile.txt'

  Scenario: Verify File upload
    Given path 'upload'
    * def location = 'classpath:/com/api/automation/fileupload/testUploadfile2.json'
    And multipart file filename = { read:'#(location)', filename:'testUploadfile2.json', Content-type:'multipart/form-data' }
    When method POST
    And status 200
    And match response.FileName =='testUploadfile2.json'
