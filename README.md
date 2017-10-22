# w-jax-2017
Präsentation und Demos zur W-JAX 2017 Session "Verteilte Konfiguration und Verwaltung sensibler Daten mit Spring Cloud Config und Vault"

[Präsentation](https://andifalk.github.io/w-jax-2017/presentation/index.html) and demos for 
[Verteilte Konfiguration und Verwaltung sensibler Daten mit Spring Cloud Config und Vault](https://jax.de/cloud-container-serverless/verteilte-konfiguration-und-verwaltung-sensibler-daten-mit-spring-cloud-config-und-vault/) at 
[W-JAX 2017](https://jax.de).

## Präsentation

Die Präsentation dieser Session kann [hier](https://andifalk.github.io/w-jax-2017/presentation/index.html) abgerufen werden.

## Spring Vault Demo

This demonstrates the usage of the _transit_ secret backend of vault in a typical spring boot based web application with JPA.
In this sample sensible data like social security and credit card numbers are encrypted before storing in the database and
decrypted before returning these to the rest api.

## Spring Cloud Config Vault Server

This demo implements a spring cloud config server both providing access to a...

* ...Git repository for all configuration properties that are not sensible
* ...Vault secret store for all sensible configuration data

## Spring Cloud Config Vault Client

This demo implements a spring cloud config client connecting to the corresponding
demo server and reads two configuration properties, one from git repo and one from vault.
