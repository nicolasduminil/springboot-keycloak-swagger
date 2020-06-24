#!/bin/sh
echo "********************************************************"
echo "Starting OAuth 2.0 Service on $OAUTH20_HOST:$OAUTH20_PORT"
echo "********************************************************"
while ! `nc -z $OAUTH20_HOST  $OAUTH20_PORT`; do sleep 3; done
echo "******* The OAuth 2.0 Provider has started"

echo "********************************************************"
echo "Starting Press Release Service on $PRESS_RELEASE_HOST:$PRESS_RELEASE_PORT"
echo "********************************************************"

java -Dserver.port=$PRESS_RELEASE_PORT -jar /usr/share/service-orchestration/press-release-management.jar
