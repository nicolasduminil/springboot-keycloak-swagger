#!/bin/sh
echo "****************************************************************"
echo "Waiting for the keycloak service to start on $KEYCLOAKSERVER_HOST:$KEYCLOAKSERVER_PORT"
echo "****************************************************************"
while ! `nc -z $KEYCLOAKSERVER_HOST  $KEYCLOAKSERVER_PORT`; do sleep 3; done
echo "******* The Keycloak Service has started"

echo "********************************************************"
echo "Starting OAuth 2.0 Service on $OAUTH20_HOST:$OAUTH20_PORT"
echo "********************************************************"

java -Dserver.port=$OAUTH20_PORT -jar /usr/share/service-orchestration/oauth-2.0-provider.jar
