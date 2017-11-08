# Spring Cloud Config Vault Server

This demo implements a spring cloud config server both providing access to a...

* ...Git repository for all configuration properties that are not sensible
* ...Vault secret store for all sensible configuration data

This is enabled by activation the spring profiles named `git` and `vault` using the
predefined property `spring.profiles.active`.

The server points to git repository on [github.com/andifalk/cloud-config-repository](https://github.com/andifalk/cloud-config-repository)

This server is configured to run on port `8888` which is by convention the default
port of a spring cloud config server.

## Preparation

1. Startup vault using `vault server -dev`
2. Add root key `vault token-create -id=myroot` as defined in property _vault.token_ in `application.yml`
3. Start the application via `./gradlew bootRun`

