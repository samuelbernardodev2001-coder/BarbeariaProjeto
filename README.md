# Barbearia — BackEnd

Projeto de BackEnd da Faculdade Alpha: aplicação back-end desenvolvida com **Spring Boot 3.4.5** que representa o ambiente de uma **barbearia**, incluindo agendamentos, clientes, funcionários, serviços, horários disponíveis, presença na loja, avaliações e fila de atendimento.

## Tecnologias

- **Java** + **Spring Boot 3.4.5**
- **Spring Web** — APIs REST
- **Spring Data JPA** — persistência
- **Spring Security** — autenticação/autorização com **JWT**
- **Maven** (wrapper `mvnw` incluído)
- Front-end estático em `src/main/resources/static` (HTML/CSS/JS) — atualmente um **mock/demo** que simula o backend em JavaScript, sem chamadas à API REST

## Estrutura do Projeto

```
com.projeto.barbearia
├── controller/     # Endpoints REST
├── service/        # Regras de negócio
├── repository/     # Acesso a dados (JPA)
├── model/          # Entidades JPA
├── dto/            # Objetos de transferência
├── config/         # Configurações (tratamento de validação)
└── security/       # JWT (JwtService, JwtAuthFilter, SecurityConfig)
```

## Modelo de Domínio

| Entidade | Descrição |
|---|---|
| `Pessoa` (MappedSuperclass) | Base com `id`, `nome`, `email`, `telefone`, `senha` |
| `Cliente` | Estende `Pessoa` — cliente da barbearia |
| `Funcionario` | Estende `Pessoa` + `cargo` (`ADMIN`, `BARBEIRO`, `ATENDENTE`) |
| `Servico` | Serviço/corte oferecido (`nome`, `preco`) |
| `Agendamento` | Agendamento de cliente com barbeiro e serviço (`dataHora`, `status`, `checkIn`) |
| `HorarioDisponivel` | Faixa de horário (`inicio`/`fim`) de um barbeiro |
| `Presenca` | Check-in/check-out físico do cliente na loja (limite de 10 clientes) |
| `Avaliacao` | Avaliação de cliente para barbeiro (nota 1–5 + comentário) |

## Endpoints da API

### Clientes — `/api/clientes`
- `POST /` — cadastra cliente (`ClienteDTO`, validado)
- `GET /` — lista todos os clientes
- `GET /{id}` — busca cliente por ID

### Funcionários — `/api/funcionarios`
- `POST /` — cadastra funcionário (**somente ADMIN**)
- `GET /` — lista funcionários
- `GET /{id}` — busca funcionário por ID

### Serviços — `/api/servicos`
- `POST /` — cadastra serviço (**somente ADMIN**)
- `GET /` — lista serviços
- `GET /{id}` — busca serviço por ID

### Agendamentos — `/api/agendamentos`
- `POST /` — cria agendamento (valida disponibilidade do barbeiro)
- `GET /` — lista agendamentos
- `GET /cliente/{clienteId}` — agendamentos de um cliente
- `PATCH /{id}/check-in` — marca check-in do agendamento
- `PATCH /{id}/reagendar` — reagenda (body: `LocalDateTime` ISO)
- `DELETE /{id}` — cancela (somente com **1h de antecedência**)

### Horários — `/api/horarios`
- `POST /` — cadastra horário disponível (**somente BARBEIRO**)
- `GET /barbeiro/{barbeiroId}` — lista horários de um barbeiro

### Presença — `/api/presenca`
- `POST /check-in?clienteId=&agendamentoId=` — check-in do cliente (limite de 10 presentes)
- `POST /check-out/{id}` — check-out
- `GET /ativos` — lista clientes presentes na loja

## Regras de Negócio

- **Agendamento**: só é permitido se o barbeiro tiver `HorarioDisponivel` cobrindo o horário (`HorarioDisponivelService.isDisponivel`).
- **Cancelamento**: bloqueado se faltarem menos de 1 hora para o agendamento.
- **Presença**: capacidade máxima de **10 clientes** simultâneos na loja.
- **Fila de atendimento**: `FilaAtendimentoService` mantém uma fila em memória (`LinkedList`) de agendamentos.
- **Dashboard**: `DashboardService.getResumo()` calcula total de agendamentos, receita total e serviços disponíveis.

## Segurança

- Autenticação via **JWT** (`JwtService` gera tokens com `email` como subject e `role` como claim; `JwtAuthFilter` valida o header `Authorization: Bearer <token>` em cada requisição e popula o `SecurityContext` com a role).
- Rotas públicas: páginas estáticas, `/api/v1/auth/**`, Swagger.
- Demais rotas exigem autenticação; endpoints sensíveis usam `@PreAuthorize` por cargo (`ADMIN`, `BARBEIRO`).
- **`ValidationHandler`** (`@RestControllerAdvice`) padroniza erros: `400` para validação (`@Valid`), `404` para `RuntimeException`/entidade não encontrada, `403` para acesso negado e `500` para erros genéricos.

> **Atenção:** ainda **não existe endpoint de login/autenticação** implementado — o `SecurityConfig` libera `/api/v1/auth/**`, mas nenhum controller gera o token JWT. Além disso, as senhas são salvas em **texto plano** (há comentário no código indicando que deveriam ser criptografadas).

## Como Executar

**Pré-requisitos:** Java 17+ e Maven (ou use `./mvnw`).

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080` e serve o front-end estático em `/`.

> **Atenção:** o `application.properties` atual **não define datasource nem as propriedades `jwt.secret`/`jwt.expiration`**. Para rodar, configure no `application.properties` (ou variáveis de ambiente) um banco de dados (ex.: PostgreSQL/MySQL/H2) e as chaves JWT:
>
> ```properties
> spring.datasource.url=jdbc:postgresql://localhost:5432/barbearia
> spring.datasource.username=usuario
> spring.datasource.password=senha
> jwt.secret=sua-chave-secreta
> jwt.expiration=86400000
> ```

## Testes

```bash
./mvnw test
```

## Observações e Pendências

- **Autenticação incompleta**: não há controller para emitir tokens JWT (login/cadastro via `/api/v1/auth/**` está liberado no security, mas não implementado).
- **Services sem controller**: `AvaliacaoService`, `DashboardService` e `FilaAtendimentoService` existem, mas não são expostos via REST.
- **Front-end mock**: `static/app.js` simula usuários/agendamentos em memória (arrays JS), sem `fetch` para a API. Além disso, `index.html` referencia `/js/script.js` e `/images/logo.png`, que **não existem** — os arquivos reais são `app.js` e `logo1.png`/`logo2.png` na raiz de `static/`.
- **Senhas em texto plano** no banco.
- **Fila de atendimento em memória** (`LinkedList` no `FilaAtendimentoService`) — se perde a cada restart e não é thread-safe.
- **Teste órfão**: `src/test/java/com/Integrador/ProjetoBackEnd/BarbeariaApplicationTests.java` está em pacote divergente do restante do projeto.

