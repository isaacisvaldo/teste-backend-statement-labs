prosefa-backend
Descrição do Projeto

Este é o serviço de backend para o desafio técnico PROSEFA.

A aplicação é construída com Spring Boot seguindo princípios de Clean Architecture (Ports & Adapters) e utiliza um banco de dados PostgreSQL para persistência. O sistema expõe uma API RESTful para operações relacionadas a empresas (Company), selos fiscais (FiscalStamp) e auditoria de eventos (AuditLog).
Requisitos

Para rodar a aplicação localmente, certifique-se de que você tem os seguintes softwares instalados:

    Java Development Kit (JDK) 21

    Apache Maven

    Docker

    Docker Compose

Tecnologias Utilizadas

    Java 21

    Spring Boot 3.3.0

    Spring Data JPA

    Spring Security

    PostgreSQL

    Lombok

    Docker e Docker Compose

    Springdoc OpenAPI (Swagger UI)

    Maven

Configuração Local
1. Iniciar a Aplicação

make up

Esse comando irá:

    Subir um container do PostgreSQL via docker-compose.yml.

    Compilar o projeto com Maven.

    Executar a aplicação Spring Boot.

Disponível em:

👉 http://localhost:8080
2. Encerrar e Limpar

make clean

Esse comando irá:

    Derrubar o container do banco.

    Remover volumes.

    Limpar artefatos do Maven.

Endpoints da API

Após rodar o projeto, a documentação interativa estará disponível em:

👉 http://localhost:8080/swagger-ui.html
Companies

    POST /api/companies → Registrar uma empresa.

    GET /api/companies → Listar empresas (com filtros e paginação).

Fiscal Stamps

    POST /api/fiscal-stamps/request → Solicitar selo fiscal.

    POST /api/fiscal-stamps/validate → Validar selo fiscal.

    GET /api/fiscal-stamps → Listar selos fiscais (com filtros e paginação).

Audit Logs

    GET /api/audit-logs → Listar registros de auditoria (com filtros e paginação).

Listagem de AuditLogs com Filtros e Paginação

Assim como em Companies, a entidade AuditLog pode ser consultada via API com suporte a filtros e paginação.

Esse recurso permite rastrear as ações realizadas no sistema de forma eficiente.
DTO de Filtro

public class AuditLogFilterDTO {
    private String entity;
    private String action;
    private String user;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    // paginação e ordenação
    private int page = 0;
    private int size = 10;
    private String sortBy = "dateTime";
    private Sort.Direction sortDirection = Sort.Direction.DESC;
}

Exemplo de Endpoint no Controller

@GetMapping
public ResponseEntity<ApiResponse<Page<AuditLog>>> getAuditLogs(@ParameterObject AuditLogFilterDTO filter) {
    Page<AuditLog> logsPage = auditLogUseCase.findAllWithFilter(filter);
    ApiResponse<Page<AuditLog>> apiResponse = ApiResponse.success(
            "Audit logs retrieved successfully.",
            HttpStatus.OK,
            logsPage
    );
    return ResponseEntity.ok(apiResponse);
}

Exemplo de Chamada

GET http://localhost:8080/api/audit-logs?page=0&size=5&user=admin&entity=FiscalStamp&sortBy=dateTime&sortDirection=DESC

Resposta

{
  "success": true,
  "message": "Audit logs retrieved successfully.",
  "status": "OK",
  "data": {
    "content": [
      {
        "id": "21ff1d2e-3c2f-4a7a-9aaf-8d1b5e50c2ab",
        "entity": "FiscalStamp",
        "action": "VALIDATION_PERFORMED",
        "audit_user": "admin",
        "dateTime": "2025-08-30T10:15:00",
        "details": "Stamp validated: PROSEFA-000123"
      }
    ],
    "totalPages": 10,
    "totalElements": 100,
    "size": 5,
    "number": 0
  }
}

Autor

Isaac Bunga

Com isso, fica claro que a listagem de AuditLog segue o mesmo padrão que já usamos para Company e FiscalStamp, mantendo consistência no design da API.