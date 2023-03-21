

Feature: API Testing
# primary token = a84f36d8a3244bdd4258541b10c4ce4e630053a013c7a91471940fc74657614b
  Background:
    Given User given api url "https://gorest.co.in/"

  @apitest
  Scenario Outline: POST-Create a new user
    Given Set api endpoint "public/v2/users"
    When User creates new user with request body "<name>" "<email>" "<gender>" "<status>"
    Then validate the status code 201
    And validate the userId is not null
    And validate the user name "<name>"
    And validate the user email "<email>"
    And validate the user gender "<gender>"
    And validate the user status "<status>"

    Examples:
      | name | email         | gender | status |
      | john | john@mail.com | male   | active |


  Scenario Outline: GET User Details
    Given Set api endpoint "public/v2/users/<id>"
    Then I get user details

    Examples:
      | id   |
      | 1010035 |


  Scenario Outline: PUT-PATCH Update user details
    Given Set api endpoint "public/v2/users/<id>"
    When update user details "<name>" "<gender>" "<status>"


    Examples:
      | id   | name | gender | status |
      | 1010035 | John | male   | active |


  Scenario Outline: POST-Creates a post comment with given ID
    Given Set api endpoint "/public/v2/posts/<id>/comments"
    And Create a comment "<body>" and post id "<id>"


    Examples:
      | id   | body                     |
      | 1010035 | Hello welcome to RESTapi |


  Scenario Outline: DELETE-Delete user
    Given Set api endpoint "/public/v2/users/<id>"
    And I delete the user id "<id>"


    Examples:
      | id   |
      | 1276 |