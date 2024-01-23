import { Component, ElementRef, ViewChild } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CartService} from "../../../shared/services/cart.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent {
  @ViewChild('dropdownButton') dropdownButton!: ElementRef;
  @ViewChild('dropdownContent') dropdownContent!: ElementRef;

  items: string[] = ['All', 'Brand', 'Price','Mileage','Id'];
  filteredItems: string[] = [];
  searchTerm: string = '';
  selectedCategory: string = 'All';

  constructor(private router : Router , private cartService : CartService,
              private route : ActivatedRoute) {
  }
  ngOnInit() {
    this.filteredItems = this.items.filter(item => item !== this.selectedCategory);
  }
  filterItems() {
    console.log('Search for :  ', this.searchTerm);
  }
  menuItemClicked(item: string) {
    console.log('Clicked:', item);
    this.selectedCategory = item;
    this.filteredItems = this.items.filter(item => item !== this.selectedCategory);
  }

  searchButtonClicked(){
      this.router.navigate(['/search-result'], {
          queryParams: {
              ['category']: this.selectedCategory,
             ['term']: this.searchTerm,
          },
      });
  }

}
