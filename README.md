curl -H "Content-Type: application/json" -X POST -d '{
    "userName": "admin",
    "password": "admin"
}' http://localhost:8080/users/create


curl -i -H "Content-Type: application/json" -X POST -d '{
    "userName": "admina",
    "password": "admin"
}' http://localhost:8080/login


curl -H "Content-Type: application/json" \
-H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1Nzk5MjYxOX0.j-zqegM24hOPZy92ND1rMDSUvHz44NAvwV5V2f0g4qs_TJ0ANTVe1Txmz4HRCcGi4BKE9p8bIJ2XPP-Ee1ghXA" \
-X POST -d '{  
   "firstName":"Sourabh",
   "lastName":"Pisal"
}'  http://localhost:8080/employee/save

curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1Nzk5MjYxOX0.j-zqegM24hOPZy92ND1rMDSUvHz44NAvwV5V2f0g4qs_TJ0ANTVe1Txmz4HRCcGi4BKE9p8bIJ2XPP-Ee1ghXA" \
localhost:8080/employee/all


curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VyTmFtZVwiOlwiYWRtaW5cIixcInBhc3N3b3JkXCI6bnVsbCxcInJvbGVzXCI6W1wiQURNSU5cIl19IiwiZXhwIjoxNTU4MDI1NTA5fQ.p2u-pWcsRA2_KRfOALSCSsnkwY8i7nFj4A_gdI0dUq4pe0bkpOqGm0hrb2cIbiTTunoypxqPuMANVporg69fAw" \
localhost:8080/employee/all