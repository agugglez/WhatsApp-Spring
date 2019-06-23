import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Subscription } from 'rxjs';
import { Message } from 'src/app/models/message';
import { ConversationsService } from '../../services/conversations.service';
import { ChatService } from 'src/app/services/chat.service';
import { UserProfile } from 'src/app/models/userProfile';
import { Conversaton } from 'src/app/models/conversation';
import { RestService } from 'src/app/services/rest.service';
import { MessOut } from 'src/app/models/messout';

@Component({
  selector: 'app-content-panel',
  templateUrl: './content-panel.component.html',
  styleUrls: ['./content-panel.component.css'],

})
export class ContentPanelComponent implements OnInit {

  user:UserProfile;
  reciver:UserProfile;
  chatroom:Conversaton;
  subscription:Subscription;  

  
  constructor(private conversationService:ConversationsService, private restService:RestService , private chatService:ChatService) { 
    
    this.chatService.messageRecieved$.subscribe(mess =>{
      let inbound = new MessOut();
      inbound.conversationId = mess.conversationId;
      inbound.from = mess.from.userID;
      inbound.txt = mess.txt;
      inbound.messageID = mess.messageID;
      this.addMessage(inbound);
    });

   this.conversationService.user$.subscribe(user=> {
     this.user = user;
     console.log(this.user);
     });
     
  }

  ngOnInit() {
    //this.restService.getUserByID("Louis Litt").subscribe(user => console.log(user));
    //this user is null and not assigned use global varable through service to acctess for all components

    this.conversationService.conversations$.subscribe(
      chatroom => { 
        this.chatroom = chatroom;
        //unsafe handling of array the arrrey contains only one member
        //console.log(chatroom.members[0]);
        this.reciver = this.chatroom.members[0];
        this.setChatroom();
      });

      
    
    $(".messages").animate({ scrollTop: $(document).height() }, "fast");
  }

   docHeight = $(document).height();
   private message:any;

  newMessage(value:string) {
    if ($.trim(value) == '') {
      return false;
      }

    let message = new Message();
    message.conversationId ='3';
    message.from = this.user;
    message.messageID = '0';
    message.txt = value;
    this.chatService.sendMessage(message);
    this.showMessage(value);
  }

  sendButtonClicked(){   
    let message:any;
    message = $(".message-input input").val();
    if ($.trim(message) == '') {
      return false;
      }
    this.newMessage(message);
  }

  setChatroom(){
    $("#board").empty();
    this.restService.getConversation(this.user.userID , this.reciver.userID).subscribe(con => {
      this.chatroom = con;
      console.log(this.chatroom);
       let messages = con.messages;
        messages.forEach(mes => {
          if(mes.from == this.user.userID)
          this.showMessage(mes.txt)
          else this.addMessage(mes);
        
        });

    });

    

  }

  addMessage(message:MessOut){

    // console.log("adding message******")
    // if(message.from.userID == this.user.userID){
    //   $('<li class="sent"><img src='+ this.user.avatar +' alt="" /><p>' + message.txt + '</p></li>').appendTo($('.messages ul'));
    //   $('.message-input input').val(null);
    //   //$('.contact.active .preview').html('<span>You: </span>' + this.message);
    //   $(".messages").animate({scrollTop: this.docHeight + 93}, "fast");      
    //   this.docHeight += 93;
    // }
    // else {
      $('<li class="replies"><img src='+ this.reciver.avatar +' alt="" /><p>' + message.txt + '</p></li>').appendTo($('.messages ul'));
      $('.message-input input').val(null);
      $('.contact.active .preview').html('<span>You: </span>' + this.message);
      $(".messages").animate({scrollTop: this.docHeight + 93}, "fast");
      this.message = '';
      this.docHeight += 93;

   // }
  }

  showMessage(message:string){ 
    $('<li class="sent"><img src='+ this.user.avatar +' alt="" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
    $('.message-input input').val(null);
    $('.contact.active .preview').html('<span>You: </span>' + message);
    $(".messages").animate({scrollTop: this.docHeight + 93}, "fast");
    
    this.docHeight += 93;
  }

}
