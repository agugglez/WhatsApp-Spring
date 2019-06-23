import { Component } from '@angular/core';
import { ConversationsService} from './services/conversations.service';
import { RestService} from './services/rest.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[ConversationsService,RestService ]
})
export class AppComponent {
  title = 'chat-frontend';
  constructor(private conversationService:ConversationsService, private restService:RestService){}
}
