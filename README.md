# SpringBoot Open ID Connect Authentication/Authorization with Keycloak and Swagger
This project demonstrates how to perform Authentication/Authorization in SpringBoot
micro-services with Keycloak and Swagger.

It consists in a SpringBoot micro-service exposing a fictious REST API which
manages press releases. A press release is a written document, a sound track or 
a video that annonces something newsworthy. Its purpose is to get attention, make
news, and generate publicity. The press release management become a very  cost
effective market, as they can be used to create brand awareness and, acoordingly,
lots of companies nowadays propose this kind of service. Hence this fictious 
micro-service.

The exposed REST API is a simple one which crudes press releases. It's a protected
API, meaning that it requires users authentication and authorization through 
OAuth 2.0 and Open ID Connect protocol, via a Keycloak service. And in order to
test the API, an Open API (Swagger) interface is provided.

A second micro-service is provided, which allows to centralize the authentication
/ authorization process. Hence, instead of configuring each micro-service in the
infrastructure in order to take advantage of the Keycloak authentication and 
authorization process, a possible design is to centralize the configuration in a
separate micro-service which is somehow proxying Keycloak. This micro-service 
might be a fully customized one, as it is the case here, or a gateway like 
Zuul, etc.

## Building a project
In order to build the project, the further steps are required:

1. Clone the GIT repository
2. Run the script `build.sh`

The build process will start the following docker containers:

1. A docker container running the jboss/keycloak image. It contains Ubuntu 
18.06 LTS and Keycloak 10 deployed in a Wildfly application server.
2. A docker container running the adoptopenjdk/openjdk11 image. It contains 
Alpin Linux, AdoptOpenJDK 11 and the oauth-2.0-provider SpringBoot 
micro-service. This might be used to proxy Keycloak in order to centralize the
authentication/authorization process, as explained above.
3. A docker container running the adoptopenjdk/openjdk11 image. It contains 
Alpin Linux, AdoptOpenJDK 11 and the press-release-management SpringBoot 
micro-service. It exposes our REST API.

The build process is running the script named customize.sh which creates a new
Keycloak realm and configures it with the users, roles and clients required by 
the sample. Don't hesitate to have a look at it and to understand who is who there
and what everything is about. Use the Keycloak administration console to inspect
the new created `press-release` realm. 

## Running
Once that you have checked that the docker containers are up and running 
(at least keycloak and press-release), you may exercice the REST API by firing 
your prefered browser at `http://192.168.96.12:8081/pr`. The Swagger interface
will display.

Now you may try out one of the APIs, for example `GET /api/all`. You'll receive
an HTTP 403 (Forbidden) return code as you aren't authenticated. In order to get
authenticated, you need to click on the small lock labeled Authorize. A login 
dialog box will display. Here you need to use the credentials `pradmin/password1`.
Keep the remaining of the login dialog box as it is, i.e. `curl` in the `client-id` 
field and nothing in the `client_secret` one. The authentication process must
succeed.

Now you may exercice the REST API as you wish. If you want to logout, then click
again the small lock and the button labeled `Logout`.

That's all fox.