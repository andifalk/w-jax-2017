# Spring Cloud Config Vault Client

This demo implements a spring cloud config client connecting to the corresponding
demo server on port `8888` and reads two configuration properties...

* ...the property `msg` is considered as not sensible and is stored in git repository
* ...the property `mysecret` is considered sensible and is stored in vault

## Preparation

1. Startup vault using `vault start -dev`
2. Note down the root token from screen output and put this as value for property _vault.token_ in `bootstrap.yml`
3. Start the application via `./gradlew bootRun`

After starting the application you can see the current values of the properties by accessing the following
web page location:

[localhost:8080/props](http://localhost:8080/props)

## Administer property values

To change the property value of `msg` change value inside of `cfgclient.properties` in git repository on [github.com/andifalk/cloud-config-repository](https://github.com/andifalk/cloud-config-repository)

To add or change the property of `mysecret` write the corresponding value to secret store of vault using following steps:

1. If not yet authenticated please authenticate using the root token:

`vault auth 6b8cdd70-727a-7ea5-3e8d-e34d69e7ae8d` (replace the token with correct value)

2. Add a value for property `mysecret`

You can either write this to the general application scoped path

`vault write /secret/application mysecret=verysecret`

Or you can write it to the application specific path

`vault write /secret/cfgclient mysecret=verysecret`

## Refresh client

To see the updated state of properties after changing any property value
you may either restart the client application or you can just post a refresh
command to the application:

To do this use _curl_ or some other rest client that is able to send post requests.
Post the request to `http://localhost:8080/refresh`.
  