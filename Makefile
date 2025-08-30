# Altere o Makefile para a versão simplificada
.PHONY: up start-db run-app clean

# Variáveis
DOCKER_COMPOSE_FILE := docker-compose.yml

# Comando principal para subir tudo
up: start-db run-app

# Inicia o container do PostgreSQL em segundo plano
start-db:
	@echo "Iniciando o container PostgreSQL..."
	docker compose -f ${DOCKER_COMPOSE_FILE} up -d

# Roda a aplicação Spring Boot
run-app:
	@echo "Rodando a aplicação Spring Boot..."
	./mvnw spring-boot:run

# Limpa o projeto e para o container
clean:
	@echo "Parando o container PostgreSQL e limpando o projeto..."
	docker compose -f ${DOCKER_COMPOSE_FILE} down
	./mvnw clean