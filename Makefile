.PHONY:

# ==============================================================================
up-local:
	@echo Starting local docker compose
	docker-compose -f docker-compose.local.yaml up -d --build
# ==============================================================================
down-local:
	@echo Stopping local docker compose
	docker-compose -f docker-compose.local.yaml down
# ==============================================================================
down-local-deep:
	@echo Stopping local docker compose and cleaning volumes
	docker-compose -f docker-compose.local.yaml down -v
# ==============================================================================
