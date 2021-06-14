import { Component, OnInit } from '@angular/core';
import { Bin2DecService } from './shared/bin2dec.service';
import { Bin2Dec } from './shared/bin2dec.model';
import { catchError } from 'rxjs/operators';


@Component({
  selector: 'app-bin2dec',
  templateUrl: './bin2dec.component.html',
  styleUrls: ['./bin2dec.component.css']
})
export class Bin2decComponent implements OnInit {
  bin2dec: Bin2Dec;
  errorMessage: String;
  loading: boolean;

  constructor(private bin2decService: Bin2DecService) { 
    this.loading = false;
  }

  ngOnInit(): void {
    
  }

  convertBinaryToDecimal(_binaryNumber: string): void {
    this.bin2dec = null;
    
    if (_binaryNumber) { 
      this.loading = true;
      this.bin2decService.convertBinaryToDecimal(_binaryNumber)
        .subscribe(
          (response) => { 
            this.bin2dec = response;
            this.errorMessage = null;
            this.loading = false;
          },
          (error) => {
            this.errorMessage = error;
            this.loading = false;
          }
        );
    } 
  }

}
