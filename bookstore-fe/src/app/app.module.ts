import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BookstoreHeaderComponent } from './views/bookstore-header/bookstore-header.component';
import { BookstoreFooterComponent } from './views/bookstore-footer/bookstore-footer.component';
import {ReactiveFormsModule} from "@angular/forms";
import { BookstoreListComponent } from './views/bookstore-list/bookstore-list.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { BookstoreDetailComponent } from './views/bookstore-detail/bookstore-detail.component';
import {MatDialogModule} from "@angular/material/dialog";
import {environment} from "../environments/environment";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    BookstoreHeaderComponent,
    BookstoreFooterComponent,
    BookstoreListComponent,
    BookstoreDetailComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatButtonToggleModule,
    MatDialogModule,
    HttpClientModule,
  ],
  exports: [
    BookstoreHeaderComponent,
    BookstoreFooterComponent
  ],
  providers: [
    { provide: 'environment', useValue: environment }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
