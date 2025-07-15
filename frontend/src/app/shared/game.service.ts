import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private baseUrl = 'http://localhost:8080/api/game';

  constructor(private http: HttpClient) {}

  getGames(pageNo = 0, pageSize = 10) {
    const params = new HttpParams()
      .set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.get<any>(`${this.baseUrl}`, { params });
  }

  getGameDetails(id: number) {
    return this.http.get(`${this.baseUrl}/details/${id}`);
  }

  addGame(game: any) {
    return this.http.post(`${this.baseUrl}/add`, game);
  }

  updateGame(id: number, game: any) {
    return this.http.put(`${this.baseUrl}/${id}/update`, game);
  }

  deleteGame(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}/delete`);
  }

  getByGenre(genre: string) {
  return this.http.get<any>(`${this.baseUrl}/by-genre-page`, {
    params: { genre, pageNo: 0, pageSize: 10 }
  });
}

getByTag(tag: string) {
  return this.http.get<any>(`${this.baseUrl}/by-tag-page`, {
    params: { tag, pageNo: 0, pageSize: 10 }
  });
}

getByDeveloper(developer: string) {
  return this.http.get<any>(`${this.baseUrl}/by-developer-page`, {
    params: { developer, pageNo: 0, pageSize: 10 }
  });
}

getByPublisher(publisher: string) {
  return this.http.get<any>(`${this.baseUrl}/by-publisher-page`, {
    params: { publisher, pageNo: 0, pageSize: 10 }
  });
}

getFilteredGames(url: string): Observable<any> {
  return this.http.get<any>(url);
}

}
