import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-city-list',
  templateUrl: './city-list.component.html',
  styleUrls: ['./city-list.component.css']
})
export class CityListComponent implements OnInit {

  cities = [ "Singapore", "Kuala Lumpur", "Saigon", "Bangkok",
    "London", "Taipei", "Tokyo", "San Francisco" ]

  constructor(private router: Router) { }

  ngOnInit(): void { }

  go(city: string) {
    const params = {
      fields: 'imperial',
    }
    this.router.navigate(['/weather', city ], { queryParams: params })
  }

}
