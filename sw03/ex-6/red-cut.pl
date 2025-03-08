% ohne Cut -> Prolog wird mit Backtracking alle kombinationen der Liste mit 3 members suchen.
?- L = [_,_,_], member(a,L), member(b,L), member(c,L).

% mit Cut -> Prolog wird Elemente links von Operator binden.
% ist ein Red-Cut, da die deklarative Bedeutung verändert.
?- L = [_,_,_], member(a,L), member(b,L), member(c,L), !.
% kann auch als: 
% ?- L = [_,_,_], member(a,L), member(b,L), !, member(c,L).
% geschrieben werden.

% was ist der Untershcied zu 
% ?- L = [_,_,_], !, member(a,L), member(b,L), member(c,L).
% member Prädikate werden erneut ausgeführt und schauen, ob a an einer anderen Stelle von L vorkommen können

