#!/bin/bash

printf "========== Executing Docker Compose with Build to start the application =========\n"
printf "\n"


printf "================ Building local Quarkus Project with local Maven =================\n"
printf "\n"
cd quarkus/
./mvnw package
cd ..
printf "\n"
printf "================ Quarkus Project builded successfully by  Maven ==================\n"
printf "\n\n\n"


printf "=============== Building local Angular Project with local Angular/NPM =============\n"
printf "\n"
cd angular/
npm install
ng build --prod
cd ..
printf "\n"
printf "=============== Angular Project builded successfully by Angular/NPM ===============\n"
printf "\n\n\n"


function ctrl_c() {
printf "\n"
printf '===================================================================================\n'
printf '================= EXECUTIN DOCKER COMPOSE DOWN AFTER CTRL+C =======================\n'
printf '===================================================================================\n'
printf "\n"
docker-compose -f ./docker-compose.yml down
exit
}
trap ctrl_c INT

docker-compose -f ./docker-compose.yml up --build

ctrl_c