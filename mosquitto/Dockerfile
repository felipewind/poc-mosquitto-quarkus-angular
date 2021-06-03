FROM eclipse-mosquitto:2.0.10
USER root
COPY mosquitto.conf /mosquitto/config/mosquitto.conf

CMD [ "mosquitto", "-c", "/mosquitto/config/mosquitto.conf" ]