import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogModule} from '@angular/material';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import { from } from 'rxjs';
import { FormGroup, FormBuilder,ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-contact-dialog',
  templateUrl: './add-contact-dialog.component.html',
  styleUrls: ['./add-contact-dialog.component.css']
})
export class AddContactDialogComponent implements OnInit {

  form: FormGroup;
    description:string;

    constructor(
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<AddContactDialogComponent>,
           
          @Inject(MAT_DIALOG_DATA) data){
        this.description = data.description;
    }

    ngOnInit() {
        this.form = this.fb.group({
            description: [this.description, []],
          
        });
    }

    save() {
        this.dialogRef.close(this.form.value);
    }

    close() {
        this.dialogRef.close();
    }

}
