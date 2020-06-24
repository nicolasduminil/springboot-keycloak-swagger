mvn -DskipTests clean install
for cname in keycloak oauth-2.0-provider press-release service-orchestration
do
  while ! docker ps -q -f name=$cname > /dev/null 2>&1
  do
    sleep 3
  done
done
sleep 3
KEYCLOAK_IP_ADDRESS=$(./service-orchestration/src/main/docker/scripts/get-keycloak-ip-address.sh)
KEYCLOAK_PORT_NUMBER=$(./service-orchestration/src/main/docker/scripts/get-keycloak-http-port.sh)
docker exec -it keycloak /opt/jboss/keycloak/customization/customize.sh $KEYCLOAK_IP_ADDRESS:$KEYCLOAK_PORT_NUMBER
