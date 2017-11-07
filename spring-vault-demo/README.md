# Spring Vault Demo
This demo shows how to use spring vault project together with vault _transit_ secret backend
to encrypt/decrypt values in the database using JPA (Java Persistence API).
During startup the demo application already creates some person test data with encrypted
sensible data like social security and credit card numbers.

## Preparation

1. Startup vault using `vault start -dev`
2. Add root key `vault token-create -id=myroot` as defined in property _vault.token_ in `application.yml`
3. Start the application via `./gradlew bootRun`

## Rest endpoints

* List all person entities with decrypted values:

  [GET /api/persons](http://localhost:8080/api/persons)

* List all person entities with encrypted values:

  [GET /api/persons/encrypted](http://localhost:8080/api/persons/encrypted)
  
## H2 console  

The encrypted data can be viewed in H2 in-memory database as well using the h2 console.

[H2 console](http://localhost:8080/h2-console)

Please use following login data:

* JDBC-Url=jdbc:h2:mem:testdb
* User=sa
* Password=_empty_
