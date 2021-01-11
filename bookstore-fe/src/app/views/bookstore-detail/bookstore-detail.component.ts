import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {BookstoreControllerService} from "../../api/build";
import {get} from "lodash";


@Component({
  selector: 'app-bookstore-detail',
  templateUrl: './bookstore-detail.component.html',
  styleUrls: ['./bookstore-detail.component.scss']
})
export class BookstoreDetailComponent implements OnInit {

  bookId: string;
  itemBook: any;
  shipBy: string;
  isError = false;
  isConfirmed = false;
  total: number;
  order: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialog: MatDialog,
    private bookstoreControllerService: BookstoreControllerService
  ) {
    this.bookId = this.data.bookId;
    this.itemBook = this.data.itemBook;
  }

  ngOnInit(): void {
  }

  closeModalDetail(): void {
    this.dialog.closeAll();
  }

  changeShipStrategy(type: string, total: number) {
    this.shipBy = type;
    this.total = total;
  }

  placeOrder(): void {
    this.isError = false;
    if (this.shipBy === null || this.shipBy === undefined) {
      this.isError = true;
      return;
    } else {
      this.isError = false;

      const param = {
        service: this.shipBy,
        cost: this.total
      }
      this.bookstoreControllerService.insertOrder(param).subscribe(
        (response) => {
          this.order = response;
          this.isConfirmed = true;
        },
        (responseError) => {
          console.log(get(responseError, 'error.errors'));
        }
      );
    }
  }

}
