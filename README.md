# Gearbox Driver

Moja propozycja projektu do konkursu [devupgrade](devupgrade.online).

Uwagi:
 - API podane w konkursie znajduje się w pakiecie `io.project.api`.
 - dodatkowo zastosowałem wzorzec obserwatora dla symulacji przekazywania sygnałów z pedału gazu do sterownika skrzyni biegów, pakiet `io.project.model.observer`.
 - przeliczenia biegów w klasie `MDynamicGearCalculator` nie ma, ze względu na brak charakterystyk dla tego trybu. Działa to na identycznej zasadzie jak w innych kalkulatorach. Natomiast jest uwzględnienie prędkości kątowej i wyłączenia zmiany biegów.
 - dla trybów agresywności zastosowałem dwa poziomy mnożników - 120% oraz 130%