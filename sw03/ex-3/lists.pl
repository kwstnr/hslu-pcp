% 3a)
% Die Liste wird rekursiv die Liste [H | T] leeren,
% der Fakt f1 true ist, und dann die Liste wieder "füllen"
% bis die komplette Liste wieder "vollständig" ist.
add_tail(X, [], [X]). % f1
add_tail(X, [H | T], [H | L1]) :- % r1
  add_tail(X, T, L1).

% 3b)
% Rekursionsbasis f1 = "Entferne irgendwas von leerer Liste ergibt leere liste".

% falls das zu entfernende Element an erster Stelle ist (r1), entferne es und arbeite mit dem Tail der Liste weiter.
% -> hierbei Cut operator links von del(T, X, L), da man hier sonst mit Backtracking immer eine 2. inkorrekte Lösung für das Beispiel
% del([a, b, c], b, L) und noch einige cases mehr bekommt. Das liegt daran, da beim zweiten Durchlauf die Regel r2 dann trotzdem nochmal gematched wird
% auch wenn der Head der Liste X entspricht (H = X). Das unterbindet man mit diesem Red-Cut.

% falls der Head der Liste nicht dem zu entfernenden Element entspricht (r2), fahre weiter mit dem Tail der liste,
% Ergebniss hat einfach bestimmt den selben Head.

del([], _, []). %f1

del([X | T], X, L) :- !, del(T, X, L). % r1
del([H | T], X, [H | L]) :- del(T, X, L). % r2
