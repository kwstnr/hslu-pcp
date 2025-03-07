:- dynamic fak_as/2.

fak(0, 1).
fak(N, F) :-  % r1
  fak_as(N,F),
  write('Hinweis: Fakultät von '),
  write(N),
  write(' war gespeichert.'),
  !.


fak(N, F) :-
  writeln('calculating'),
	N > 0,
	N1 is N - 1,
	fak(N1, F1),
	F is N * F1,
  asserta(fak_as(N, F)).

% 2a) **** Was passiert hier? Es passiert etwas! ****
% sobald Fak berechnet, wird es als Fakt fak_as(N, F). "memoized".
% die fak r1 versucht den fakt fak_as/2 zu finden, falls dieser true ist,
% ruft es die write prädikate auf und
% unterbindet mit einem greencut die erneute ausführung der rekursiven fak/2 rule.

% 2b)
fak_clear :- 
  write("Hinweis, alle gespeicherten Werte wurden gelöscht"),
  retractall(fak_as(_, _)).
