# Spring Reactive Vault Demo
This demo shows how to use spring vault project together with 
reactive spring web (flux) and reactive spring security.
During startup the demo application already creates some book test data and
initializes an in-memory user store with secret password stored in vault.

## Preparation

1. Startup vault using `vault server -dev`
2. Add root key `vault token-create -id=myroot` as defined in property _vault.token_ in `application.yml`
3. Add the secrets: `vault write secret/reactive-spring-vault userpass=password adminpass=secret`
4. Start mongodb (for reactive data access) on localhost
5. Start the application via `./gradlew bootRun`

## Rest endpoints

* List all books:

  [GET /api/books](http://localhost:8080/api/books)
  
Please use following login data:

Standard user
* User=user
* Password=password

Admin user
* User=admin
* Password=secret