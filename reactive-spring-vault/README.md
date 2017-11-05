# Spring Reactive Vault Demo
This demo shows how to use spring vault project together with 
reactive spring web (flux) and reactive spring security.
During startup the demo application already creates some book test data and
initializes an in-memory user store with secret password stored in vault.

## Preparation

1. Startup vault using `vault start -dev`
2. Note down the root token from screen output and put this as value for property _vault.token_ in `application.yml`
3. Start the application via `./gradlew bootRun`

## Rest endpoints

* List all person entities with decrypted values:

  [GET /api/books](http://localhost:8080/api/books)
  
Please use following login data:

* User=user
* Password=mysecret
