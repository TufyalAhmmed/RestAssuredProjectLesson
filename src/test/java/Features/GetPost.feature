Feature:Verify different GEt operations using Rest-Assured

  Scenario:Verify one author of the post
    Given I perform GET operation for "/posts"
    And I perform GET for the post number "1"
    Then I should see the author name as "Tufyal"

  Scenario:Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names

  Scenario:Verify Parameter of Get
    Given I perform GET operation for "/posts"
    Then I should verify GET Parameter

  Scenario:Verify Parameter of Get Query
    Given I perform GET operation for "/posts"
    Then I should verify Query Parameter

  Scenario:Verify POST operation
    Given I should perform POST operation for "/posts"

  Scenario:Verify POST operation for profile
    Given I perform POST operation for "/posts/{profileNo}/profile"with body
    |name|profile|
    |Sams|2      |
    Then I should see the body has name as "Sams"


