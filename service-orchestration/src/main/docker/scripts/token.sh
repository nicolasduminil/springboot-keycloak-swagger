RESULT=$(curl --data "grant_type=password&client_id=curl&username=customer-admin&password=admin" http://192.168.96.2:8080/auth/realms/master/protocol/openid-connect/token)
echo $RESULT
TOKEN=$(echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g')
echo $TOKEN
curl -i -v http://192.168.96.12:8090/pr/all --header "Authorization: Bearer $TOKEN"
