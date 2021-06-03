import { Component, OnDestroy, OnInit } from '@angular/core';
import { IMqttMessage, MqttService } from 'ngx-mqtt';
import { Subscription } from 'rxjs';

const topicName = 'chat';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  private subscription: Subscription;
  name: string = '';
  connected: boolean = false;
  messages: string[] = [];
  messageToSend: string = '';

  constructor(private _mqttService: MqttService) { }

  ngOnInit(): void {
    this.subscription = this._mqttService.observe(topicName).subscribe((topicMessage: IMqttMessage) => {
      this.printMessage(topicMessage.payload.toString());
    });
  }

  ngOnDestroy(): void {
    this.disconnect();
    this.subscription.unsubscribe();
  }

  connect(): void {
    this.connected = true;
    this.publish(`User ${this.name} joined`);
  }

  disconnect(): void {
    this.publish(`User ${this.name} left`);
    if (this.connected) {
      this.connected = false;
    }
  }

  publish(messageToTopic: string): void {
    this._mqttService.unsafePublish(topicName, messageToTopic, { qos: 1, retain: false });
  }

  sendMessage(): void {
    this.publish(`>> ${this.name}: ${this.messageToSend}`);
    this.messageToSend = '';
  }

  printMessage(msg: string): void {
    if (this.messages.length >= 20) {
      this.messages = this.messages.slice(1, 20);
    }
    this.messages.push(msg);
  }

}
