% 3a)
% Die Liste wird rekursiv die Liste [H | T] leeren,
% der Fakt f1 true ist, und dann die Liste wieder "füllen"
% bis die komplette Liste wieder "vollständig" ist.
add_tail(X, [], [X]). % f1
add_tail(X, [H | T], [H | L1]) :- % r1
  add_tail(X, T, L1).
  
