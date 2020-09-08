import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TempComponent } from './Components/temp/temp.component'
import { Temp2Component } from './Components/temp2/temp2.component'
import { LoginComponent } from './Components/login/login.component'
import { ExcelImportComponent} from './Components/excel-import/excel-import.component'
import { ManageCompaniesComponent} from './Components/manage-companies/manage-companies.component'
import { CompaniesAdminComponent } from './Components/companies-admin/companies-admin.component'
import { CompaniesAddComponent } from './Components/companies-add/companies-add.component'
import { CompaniesEditComponent } from './Components/companies-edit/companies-edit.component'
import { CompaniesStockExchangeComponent } from './Components/companies-stock-exchange/companies-stock-exchange.component'
import { StockExchangeAdminComponent } from './Components/stock-exchange-admin/stock-exchange-admin.component'
import { StockExchangeAddComponent } from './Components/stock-exchange-add/stock-exchange-add.component'
import { StockExchangeEditComponent } from './Components/stock-exchange-edit/stock-exchange-edit.component'
import { IpoAdminComponent } from './Components/ipo-admin/ipo-admin.component'
import { IpoAddComponent } from './Components/ipo-add/ipo-add.component'

const routes: Routes=[
  //{path:'t1', component:TempComponent},
  //{path:'t2', component:Temp2Component},
  {path: 'importExcel', component:ExcelImportComponent},
  {path: 'companiesAdmin', component:CompaniesAdminComponent},
  {path: 'companiesAdd', component:CompaniesAddComponent},
  {path: 'companiesSE', component:CompaniesStockExchangeComponent},
  {path: 'companiesEdit', component:CompaniesEditComponent},
  {path: 'seAdmin', component:StockExchangeAdminComponent},
  {path: 'seAdd', component:StockExchangeAddComponent},
  {path: 'seEdit', component:StockExchangeEditComponent},
  {path: 'ipoAdmin', component:IpoAdminComponent},
  {path: 'ipoAdd', component:IpoAddComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
