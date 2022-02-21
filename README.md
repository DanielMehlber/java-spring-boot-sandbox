# Spring Boot Sandbox

## Testing
There are multiple approaches when it comes to (integration) testing. 
The main problem about testing web applications is their dependency to other systems (e.g. databases).
Somehow they have to be mocked or generated in order to test the applications REST-API completely.

### Manual installation and setup
The necessary systems such as databases can be installed and configured manually.
* Installation and configuration must be performed manually, additional effort
* Not very deterministic
* Not considered state of the art

### Compose Containers of project working tree and necessary systems
There are containers involved:
* Project working tree is put into container by `Dockerfile-Test`
* Other systems

They are all started by `docker-compose-test.yml` and tests are executed inside container.

* Very slow, maven has to download packages into container each time
* Easy to start and stop

### Compose Containers of context systems only
Temporary systems are created by `docker-compose-environment.yml` inside containers 
and are terminated after tests are over.

The tests are executed on the local machine, not inside a container.
