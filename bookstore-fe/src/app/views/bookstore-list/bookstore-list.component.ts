import {Component, Input, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {BookstoreDetailComponent} from "../bookstore-detail/bookstore-detail.component";
import {get} from "lodash";
import {BookstoreControllerService} from "../../api/build/api/bookstoreController.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-bookstore-list',
  templateUrl: './bookstore-list.component.html',
  styleUrls: ['./bookstore-list.component.scss']
})
export class BookstoreListComponent implements OnInit {

  bookList: object
  form: FormGroup;
  @Input() keyword: string;

  constructor(
    public dialog: MatDialog,
    private fb: FormBuilder,
    private bookstoreControllerService: BookstoreControllerService) { }

  ngOnInit(): void {
    this.initForm();
   this.getBookList();

  }

  initForm(): void {
    this.form = this.fb.group({
      keyword: [null, Validators.compose([]
      )]
    });
  }

  onSearchData(): void {
    const controls = this.form.controls;
    if (controls.keyword.value !== undefined && controls.keyword.value !== " ") {
      this.keyword = controls.keyword.value;
      this.getBookList();
    }
  }

  showBookDetail(id: string): void {
    this.bookstoreControllerService.getBookDetail(id).subscribe(
      (response) => {
        const dialogRef = this.dialog.open(BookstoreDetailComponent, {
          data: {
            bookId: id,
            itemBook: response
          }
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log("Dialog result");
        });
      },
      (responseError) => {
        console.log(get(responseError, 'error.errors'));
      }
    );

  }

  getBookList(): void {
    const param =  {
      keyword:(this.keyword) ? this.keyword : "all",
    };
    this.bookstoreControllerService.getBookstoreList(param).subscribe(
      (response) => {
        this.bookList = response;
      },
      (responseError) => {
      console.log(get(responseError, 'error.errors'));
      }
    );
  }

  handlePreventKey($event: KeyboardEvent) {
    let inputValue = $event.keyCode;
    if(!(inputValue >= 65 && inputValue <= 120)
      && !(inputValue >= 48 && inputValue <= 57)
      && !(inputValue >= 32 && inputValue <= 40)
      && (inputValue != 12 && inputValue != 8 && inputValue != 0)) {
      return false;
    }
  }

}
