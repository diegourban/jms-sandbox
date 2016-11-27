# jms-sandbox

## Sandbox project to play around with JMS + ActiveMQ

### Installation
1. Download and install ActiveMQ;
2. Start ActiveMQ `\bin\activemq.bat start`;
3. Access ActiveMQ WebConsole(http://localhost:8161/admin);
4. Create Queues and Topics based on the configurations; (check \src\main\java\jndi.properties)

### Usage
1. Run `TesteProdutorQueue.java` to produce messages to the queue;
2. Run `TesteConsumidorQueue.java` to consume messages from the queue;
3. Run `*Topic.java` to work with topics.
