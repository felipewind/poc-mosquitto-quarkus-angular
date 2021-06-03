# Mosquitto

[Webpage](https://mosquitto.org/)

[GitHub](https://github.com/eclipse/mosquitto)

[DockerHub](https://hub.docker.com/_/eclipse-mosquitto)


# Configuring WebSocket

This image is a simple copy of Tiago Stutz project:
- [GitHub tiagostutz/eclipse-mosquitto-ws](https://github.com/tiagostutz/eclipse-mosquitto-ws)

Here are some ways to run the Eclipse Mosquitto MQTT providing TCP on `1883` and at HTTP/Websocket at `9001`.

## Using my image from DockerHub

### Running with Docker run

```
$ docker run -it -p 1883:1883 -p 9001:9001 --name mosquitto --rm felipewind/eclipse-mosquitto-ws:2.0.10
```


### With docker-compose

Just bring up the following docker-compose.yml:

```yaml
version: "3.7"

services:

  mqtt:
    image: felipewind/eclipse-mosquitto-ws:2.0.10
    restart: always
    network_mode: bridge
    ports:
      - 1883:1883
      - 9001:9001
```

You are ready to connect at TCP on `1883` and at HTTP/Websocket at `9001`


## Running the original Docker image with a mosquitto.conf file to provide websockets

```
$ docker run -it -p 1883:1883 -p 9001:9001 --name mosquitto --rm --mount type=bind,src=${PWD}/mosquitto.conf,dst=/mosquitto/config/mosquitto.conf eclipse-mosquitto:2.0.10
```



# Places to study

https://stackoverflow.com/questions/34408624/how-do-i-enable-both-tcp-and-web-sockets-in-mosquitto

http://ddewaeletest.github.io/testblog/iot/mqtt/2015/09/21/building-mosquitto-with-websocket-support.html

http://www.steves-internet-guide.com/mqtt-websockets/

https://www.hivemq.com/blog/mqtt-essentials-special-mqtt-over-websockets/


## MQTT softwares

https://www.hivemq.com/

https://github.com/mqttjs/MQTT.js

https://github.com/eclipse/paho.mqtt.javascript

https://github.com/jpmens/simple-mqtt-websocket-example
- Uses Paho MQTT JavaScript client
- Just clone this project and open the index.html

## Web clients to test

http://www.hivemq.com/demos/websocket-client/

https://mitsuruog.github.io/what-mqtt/
- Uses mqtt.js

