# a) Wie manifestiert sich dieses Problem in SWI-Prolog?
Es wird nach einer Lösung suchen bis es einen Stack-Overflow gibt.

# b) Wieso tritt dieses Problem auf?
Die Rekursionsbasis wird nie zutreffen, es wird { F = F1 + F2 } ausgeführt mit 3 nicht instanzierten Variabeln im Reellen Zahlenbereich, dies kann ewig weitergehen.
F = F1 + F2 ist im reellen zahlenbereich unendlich mal lösbar.
Von Google: "Die Menge der reellen Zahlen ist unendlich groß"

es gibt auch unendlich viele Fibonacci Zahlen also wird versucht bis eine Lösung gefunden wird -> ewig/bis stack-overflow

# c)
Gibt keine Lösung ausser Zahlenbereich einzuschränken.
