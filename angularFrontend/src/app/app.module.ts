import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { MainControlPanelComponent } from './components/main-control-panel/main-control-panel.component';
import { ContentPanelComponent } from './components/content-panel/content-panel.component';
import { AddContactDialogComponent} from './components/add-contact-dialog/add-contact-dialog.component';
import { MatDialogModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatRippleModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component'; 
import { MaincontainerComponent } from './components/maincontainer/maincontainer.component';
import { ConversationsService } from './services/conversations.service';
import { RestService } from './services/rest.service';
import { ChatService } from './services/chat.service';





@NgModule({
  declarations: [
    AppComponent,
    MainControlPanelComponent,
    MaincontainerComponent,
    ContentPanelComponent,
    AddContactDialogComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatDialogModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule
  ],
  providers: [ConversationsService , RestService , ChatService],
  bootstrap: [AppComponent],
  entryComponents:[AddContactDialogComponent]
})
export class AppModule { }
