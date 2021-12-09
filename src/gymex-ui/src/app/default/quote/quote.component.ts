import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-quote',
  templateUrl: './quote.component.html',
  styleUrls: ['./quote.component.css']
})
export class QuoteComponent implements OnInit {

  quotes: string[] = [
    "“Nie przestaję, kiedy jestem zmęczony, przestaję, kiedy skończę.” – David Goggins",
    "“Ciało osiąga to, w co umysł wierzy”",
    "“Nie bój się być początkującym.”",
    "“Różnica między chęcią a osiąganiem to dyscyplina.”",
    "“Motywacja jest tym, co daje Ci początek. Przyzwyczajenie jest tym, co Cię napędza.”",
    "“Jedyny zły trening to ten, który się nie odbył.”",
    "“To, co dziś wydaje się niemożliwe, pewnego dnia stanie się Twoją rozgrzewką.”",
    "“Ból, który odczuwasz dzisiaj, będzie siłą, którą poczujesz jutro.”",
    "“Nie ma dróg na skróty, aby żyć swoim największym życiem i stać się najlepszym sobą.”",
    "“Jeśli po treningu nadal wyglądasz dobrze, oznacza to, że nie trenowałeś wystarczająco ciężko.”",
    "“Nie rezygnuj ze swoich marzeń, bo twoje marzenia zrezygnują z ciebie.” – John Drewniany",
    "“Jedyną osobą, którą masz się stać, jest osoba, którą zdecydujesz się zostać. - Ralph Waldo Emerson",
    "“Sukces nie zawsze polega na wielkości. Chodzi o spójność. Konsekwentna ciężka praca przynosi sukces. Wielkość nadejdzie.”"
  ]
  randomQuote: string

  constructor() {
  }

  ngOnInit(): void {
    this.changeQuote()
  }

  changeQuote() {
    this.randomQuote = this.quotes[Math.floor(Math.random() * this.quotes.length)];

    setTimeout(() => {
      this.changeQuote()
    }, 5000);

  }

}
