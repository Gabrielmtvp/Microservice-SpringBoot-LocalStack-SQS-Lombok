# Microserviço com Spring Boot, LocalStack, SQS e Lombok

## Visão Geral

Este projeto é um microserviço desenvolvido com **Spring Boot**, integrado ao **AWS SQS** para gerenciamento de filas de mensagens. Utiliza o **LocalStack** para simular serviços AWS localmente durante desenvolvimento e testes, e o **Lombok** para reduzir código boilerplate nas classes Java, tornando o desenvolvimento mais eficiente.

---

## Funcionalidades

- **Arquitetura de Microserviços com Spring Boot**: Design modular, escalável e fácil de manter.
- **Integração com AWS SQS**: Suporte a filas de mensagens para processamento assíncrono.
- **LocalStack**: Simulação local de serviços AWS, ideal para desenvolvimento e testes sem custos.
- **Lombok**: Reduz a verbosidade do código Java com anotações práticas.
- **Suporte a Docker**: LocalStack executado em um ambiente containerizado para maior portabilidade.

---

## Pré-requisitos

- **Java 17 ou superior**: Certifique-se de que está instalado e configurado no ambiente.
- **Maven 3 ou superior**: Necessário para gerenciar dependências e compilar o projeto.
- **Docker**: Requerido para executar o LocalStack em um container.

---

## Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/ikauedev/Microservice-SpringBoot-LocalStack-SQS-Lombok.git
cd Microservice-SpringBoot-LocalStack-SQS-Lombok
```

### 2. Iniciar o LocalStack

Certifique-se de que o Docker está em execução e execute:

```bash
docker-compose up -d
```

Isso iniciará o LocalStack com suporte ao SQS em segundo plano.

### 3. Compilar o Projeto

```bash
mvn clean install
```

Este comando baixa as dependências e gera o artefato do projeto.

### 4. Executar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## Configuração

Edite o arquivo `application.yml` para configurar as filas SQS utilizadas no projeto:

```yaml
localstack:
  host: localhost
aws:
  region: us-east-1
  sqs:
    process-order-queue: http://localhost:4566/000000000000/process-order-queue
    inventoryUpdateQueueUrl: http://localhost:4566/000000000000/inventory-update-queue
    paymentProcessingQueueUrl: http://localhost:4566/000000000000/payment-processing-queue
    shippingNotificationQueueUrl: http://localhost:4566/000000000000/shipping-notification-queue
  access-key: test
  secret-key: test
```

Essas configurações apontam para as filas simuladas pelo LocalStack.

---

## Testando a Integração com SQS

### Enviar uma Mensagem para a Fila de Processamento de Pedidos

Use o comando abaixo para enviar uma requisição POST:

```bash
curl --location --request POST 'http://localhost:8080/checkout/create-payment'
```

Isso simulará o envio de uma mensagem à fila configurada.

---

## Tecnologias Utilizadas

- **Spring Boot**: Framework robusto para criação de aplicações Java.
- **AWS SQS**: Serviço de filas simples para comunicação assíncrona.
- **LocalStack**: Ferramenta para simular serviços AWS localmente.
- **Lombok**: Biblioteca que elimina código repetitivo em Java.
- **Docker**: Plataforma de containerização para rodar o LocalStack.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.

---

## Licença

Este projeto está licenciado sob a **Licença MIT**. Consulte o arquivo `LICENSE` para mais detalhes.

---

## Contribuições

Contribuições são bem-vindas! Para colaborar, envie um **pull request** ou abra uma **issue** no repositório.