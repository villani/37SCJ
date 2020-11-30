## FIAP 37SCJ - SERVERLESS ARCHITECTURE

Projeto da disciplina de Serverless Architecture que consiste em uma API rest para gerenciamento de viagens, podendo ser executada localmente, ou disponibilizada
em uma conta da AWS.

## Requisitos

* AWS CLI already configured with at least PowerUser permission
* [Java SE Development Kit 8 installed](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Docker installed](https://www.docker.com/community-edition)
* [Maven](https://maven.apache.org/install.html)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Python 3](https://docs.python.org/3/)

## Processo de instalação

### Instalação de dependências

É utilizado `maven` para instalar as dependências e gerar o arquivo .jar do projeto.

```bash
mvn install
```

### Executando localmente

**É possível fazer chamadas a API localmente desde que configurado ambiente local**
1. Inicie o DynamoDB, utilizando o seguinte comando docker. `docker run -p 8000:8000 -v $(pwd)/local/dynamodb:/data/ amazon/dynamodb-local -jar DynamoDBLocal.jar -sharedDb -dbPath /data`
2. Criação da tabela trip no DynamoDB. `aws dynamodb create-table --table-name trip --attribute-definitions AttributeName=country,AttributeType=S AttributeName=dateTimeCreation,AttributeType=S AttributeName=city,AttributeType=S AttributeName=reason,AttributeType=S --key-schema AttributeName=country,KeyType=HASH AttributeName=dateTimeCreation,KeyType=RANGE --local-secondary-indexes 'IndexName=cityIndex,KeySchema=[{AttributeName=country,KeyType=HASH},{AttributeName=city,KeyType=RANGE}],Projection={ProjectionType=ALL}' 'IndexName=reasonIndex,KeySchema=[{AttributeName=country,KeyType=HASH},{AttributeName=reason,KeyType=RANGE}],Projection={ProjectionType=ALL}' --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000`

Se a tabela já existir e por algum motivo for necessário apaga-lá: `aws dynamodb delete-table --table-name trip --endpoint-url http://localhost:8000`

3. Inicie a API local do SAM.
 - No Mac: `sam local start-api --env-vars src/test/resources/test_environment_mac.json`
 - No Linux: `sam local start-api --env-vars src/test/resources/test_environment_linux.json`
 

Se não houve nenhum erro nos passos anteriores, é possível fazer uma chamada a API no endereço
`http://127.0.0.1:3000/trips/uruguai`. deve ser retornado 404, para testar a API, pode ser usado o arquivo  src/test/resources/37SCJ-TRIPs.postman_collection.json para importar uma collection no Postman

**SAM CLI** é usado para simular tanto a Lambda quanto o API Gateway localmente, a partir do arquivo `template.yaml`.


## Packaging and deployment

Primeiro será criada uma váriavel `S3 bucket` onde será feito o upload das Lambdas.

```bash
export BUCKET_NAME=my_cool_new_bucket
aws s3 mb s3://$BUCKET_NAME
```

A seguir, executamos o comando para empacotar nossa função Lambda no S3:

```bash
sam package \
    --template-file template.yaml \
    --output-template-file packaged.yaml \
    --s3-bucket $BUCKET_NAME
```

A seguir, o comando abaixo irá criar uma Stack no Cloudformation e fazer o deploy dos nossos recursos SAM.

```bash
sam deploy \
    --template-file packaged.yaml \
    --stack-name trips-serverless \
    --capabilities CAPABILITY_IAM
```

Depois do deploy completo, pode executar o comando abaixo para obter o endereço do API Gateway:

```bash
aws cloudformation describe-stacks \
    --stack-name sam-orderHandler \
    --query 'Stacks[].Outputs'
```
