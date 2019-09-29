import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;
  private int: any;
  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (true) {

      // tslint:disable-next-line:max-line-length
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue + '&app_id=e69e7b99&app_key=c3000ef8328698f2bb839584b954eedb&from=0&to=4').subscribe((feed: any) => {
        this.recipeList = Object.keys(feed.hits).map(function (k) {
          const i = feed.hits[k].recipe;
          return {name: i.label, icon: i.image, url: i.url};
        });
        console.log(feed.hits);
      });
    }

    if (true) {

      // tslint:disable-next-line:max-line-length
      this._http.get('https://api.foursquare.com/v2/venues/search?client_id=Q3TP1OEGTHXWSE0SHKDAPY51Z4TTWICMBGXXQPJGJB3TSDEE&client_secret=12INF5SBO0IEFYDYUJEZEDVZBMC2ZJR0TNNGJQYIYTGZQHLZ&v=20180323&limit=5&near='
        + this.placeValue + '&query=' + this.recipeValue).subscribe((feed: any) => {
        console.log(feed);
        this.venueList = Object.keys(feed.response.venues).map(function (k) {
          const i = feed.response.venues[k];
          return {name: i.name, formattedAddress: i.location.formattedAddress};
        });
      });

    }

  }
}


