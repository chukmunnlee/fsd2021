import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Weather } from '../models';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  weather!: Weather
  city = ''
  fields = ''

  constructor(private activtedRoute: ActivatedRoute, private weatherSvc: WeatherService) { }

  ngOnInit(): void {
    this.city = this.activtedRoute.snapshot.params['city']
    this.fields = this.activtedRoute.snapshot.queryParams['fields']
    console.info('fields = ', this.fields)

    this.weatherSvc.getWeather(this.city)
      .then(w => this.weather = w)
  }

}
