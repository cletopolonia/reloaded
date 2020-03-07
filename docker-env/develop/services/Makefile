info:
	@echo "Make command:"
	@echo " - prepare"
	@echo " - up"
	@echo " - up-force"
	@echo " - up-show"
	@echo " - down"
	@echo " - stop"
 
prepare:
	./prepare.sh

up:
	./prepare.sh
	cd compose && docker-compose up --force-recreate -d 

up-force:
	./prepare.sh
	cd compose && docker-compose up --force-recreate -d

up-show:
	export DOCKER_CLIENT_TIMEOUT=120
	export COMPOSE_HTTP_TIMEOUT=120
	./prepare.sh
	cd compose && docker-compose up --force-recreate

down:
	./prepare.sh
	cd compose && docker-compose down

stop:
	./prepare.sh
	cd compose && docker-compose stop

update-admin:
	cd compose && docker-compose up --no-deps -d  dvc-analytics-admin

update-search:
	cd compose && docker-compose up --no-deps -d  dvc-analytics-search

update-task:
	cd compose && docker-compose up --no-deps -d  dvc-analytics-task

update-ingestion:
	cd compose && docker-compose up --no-deps -d  dvc-analytics-ingestion

update-webgui:
	cd compose && docker-compose up --no-deps -d  dvc-analytics-webgui
