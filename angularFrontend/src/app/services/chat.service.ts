import { Injectable, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as Sockjs from 'sockjs-client';
import {Subject} from 'rxjs';
  import { from } from 'rxjs';
import { Message } from '../models/message';
import { ConversationsService } from './conversations.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService implements OnInit{

  ngOnInit(): void {}

  private messageRecieved = new Subject<Message>();
  messageRecieved$ = this.messageRecieved.asObservable();
  
  private serverUrl = 'http://10.10.69.83:8080/chatservice';
  private socket = new Sockjs(this.serverUrl);
  private stompClient = Stomp.over(this.socket);
  private messageSource = new Subject<String>();
  messsage$ = this.messageSource.asObservable();

  constructor(private conversationService:ConversationsService){ 
    this.serverUrl = 'http://10.10.69.83:8080/chatservice';
    this.socket = new Sockjs(this.serverUrl);
    
    this.stompClient = Stomp.over(this.socket);

    this.messageSource = new Subject<String>();
    this.messsage$ = this.messageSource.asObservable();
    this.initializeWebSocketConnection();
    
  }

   initializeWebSocketConnection(){
   
    this.stompClient.connect({},frame=>{
      
      this.stompClient.subscribe('/user/topic/register', sessionId => {
        
      });
      
      this.stompClient.subscribe('/user/topic/message', msgOut => {
        //handle messages
        let Obj:any = JSON.parse(msgOut.body);
        let obj2:Message = <Message>Obj;
        console.log("message recieved :" + obj2.txt );
        this.messageRecieved.next(obj2);
       
      });

      this.conversationService.setConnected();
      


    });   
   }

   

  registerSession(userID:string ){
    console.log("register calls " + userID)
    this.stompClient.send("/app/register" , {}, userID);
  }

  sendMessage(message:Message){
    this.stompClient.send("/app/message" , {}, JSON.stringify(message)); 
  }
}
