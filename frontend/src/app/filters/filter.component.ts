import { Component } from '@angular/core';
import { GameService } from '../shared/game.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
})
export class FilterComponent {
  filters = {
    genre: '',
    tag: '',
    developer: '',
    publisher: '',
    pageNo: 0,
    pageSize: 10
  };

  games: any[] = [];
  loading = false;
  error = '';

  constructor(private gameService: GameService) {}

  applyFilters() {
    this.loading = true;
    this.error = '';
    this.games = [];

    let endpoint = '';
    let queryParam = '';

    // Determine which filter is active
    if (this.filters.genre) {
      endpoint = 'by-genre-page';
      queryParam = `genre=${this.filters.genre}`;
    } else if (this.filters.tag) {
      endpoint = 'by-tag-page';
      queryParam = `tag=${this.filters.tag}`;
    } else if (this.filters.developer) {
      endpoint = 'by-developer-page';
      queryParam = `developer=${this.filters.developer}`;
    } else if (this.filters.publisher) {
      endpoint = 'by-publisher-page';
      queryParam = `publisher=${this.filters.publisher}`;
    } else {
      this.loading = false;
      this.error = 'Please enter at least one filter.';
      return;
    }

    const url = `/api/game/${endpoint}?${queryParam}&pageNo=${this.filters.pageNo}&pageSize=${this.filters.pageSize}`;

    this.gameService.getFilteredGames(url).subscribe({
      next: (response: any) => {
        this.games = response.content || response; // Handles both paginated and plain arrays
        this.loading = false;
      },
      error: (err: any) => {
        console.error(err);
        this.error = 'Failed to fetch games. Please try again.';
        this.loading = false;
      }
    });
  }
}
