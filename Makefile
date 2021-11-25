APPLICATION_CONTAINER_NAME=aspect-oriented-programming
down:
	@docker-compose down --v
run:
	@$(MAKE) down
	@$(MAKE) tests
	docker-compose up -d --build
	docker logs ${APPLICATION_CONTAINER_NAME} --follow
tests:
	@$(MAKE) down
	./gradlew clean build test