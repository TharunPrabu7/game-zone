import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GameService } from '../shared/game.service';

@Component({
  selector: 'app-game-form',
  templateUrl: './game-form.component.html'
})
export class GameFormComponent implements OnInit {
  form: FormGroup;
  editMode = false;
  gameId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private gameService: GameService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      name: [''],
      metacritic: [''],
      released_date: ['']
    });
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.editMode = true;
        this.gameId = +params['id'];
        this.gameService.getGameDetails(this.gameId).subscribe((game: any) => {
          this.form.patchValue(game);
        });
      }
    });
  }

  onSubmit() {
    if (this.editMode && this.gameId != null) {
      this.gameService.updateGame(this.gameId, this.form.value).subscribe(() => {
        this.router.navigate(['/games']);
      });
    } else {
      this.gameService.addGame(this.form.value).subscribe(() => {
        this.router.navigate(['/games']);
      });
    }
  }
}
