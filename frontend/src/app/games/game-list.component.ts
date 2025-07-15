import { Component, OnInit } from '@angular/core';
import { GameService } from '../shared/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html'
})
export class GameListComponent implements OnInit {
  games: any[] = [];
  pageNo: number = 0;
  pageSize: number = 10;

  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.loadGames();
  }

  loadGames() {
    this.gameService.getGames(this.pageNo, this.pageSize).subscribe(data => {
      this.games = data.content || data;
    });
  }

  nextPage() {
    this.pageNo++;
    this.loadGames();
  }

  prevPage() {
    if (this.pageNo > 0) {
      this.pageNo--;
      this.loadGames();
    }
  }
}
