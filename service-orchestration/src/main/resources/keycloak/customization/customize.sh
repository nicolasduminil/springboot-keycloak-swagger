#!/bin/bash
KCADM=/opt/jboss/keycloak/bin/kcadm.sh
$KCADM config credentials --server http://$1/auth --realm master --user admin --password admin
$KCADM create realms -s realm=press-release -s enabled=true
$KCADM create users -r press-release -s username=pradmin -s enabled=true
$KCADM set-password -r press-release --username pradmin --new-password password1
PRCLIENT_ID=$($KCADM create clients -r press-release -s clientId=prclient -s bearerOnly="true" -s enabled=true -s clientAuthenticatorType=client-secret -s secret=thisissecret -i)
$KCADM create clients -r press-release -s clientId=curl -s publicClient="true" -s directAccessGrantsEnabled="true" -s "redirectUris=[\"http://192.168.96.12:8081\", \"http://localhost:8081\"]" -s enabled=true -s standardFlowEnabled="false"
$KCADM create clients/$PRCLIENT_ID/roles -r press-release -s name=prmanager
$KCADM create roles -r press-release -s name=prmanager
$KCADM add-roles --uusername pradmin --rolename prmanager -r press-release
$KCADM add-roles -r press-release --uusername pradmin --cclientid prclient --rolename prmanager