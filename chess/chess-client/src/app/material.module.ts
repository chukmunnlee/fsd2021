import {NgModule} from "@angular/core";

import { MatFormFieldModule } from '@angular/material/form-field'
import { MatSelectModule } from '@angular/material/select'
import { MatButtonModule } from '@angular/material/button'
import { MatIconModule } from '@angular/material/icon'

const matModules: any[] = [
	MatIconModule, MatFormFieldModule, 
	MatSelectModule, MatButtonModule
]

@NgModule({
	imports: matModules,
	exports: matModules,
})
export class MaterialModule { }
