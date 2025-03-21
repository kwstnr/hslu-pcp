set_difference([], _, []).

set_difference([HL1 | TL1], L2, [HL1 | R]) :-
  \+ member(HL1, L2),
  !,
  set_difference(TL1, L2, R).

set_difference([_ | TL1], L2, R) :-
  set_difference(TL1, L2, R).

% Basisfall: Ist Set1 leer, ist auch die Differenz leer.
% Falls das Erste Element von Set1 im Set2 (L2) vorkommt, verwerfe HL1 und mache mit dem Tail von Set1 weiter
% Falls das Erste Element von Set2 im Set2 (L2) nicht vorkommt, nimm es ins Resultat ein und mache weiter mit dem Tail von Set1.
% Cut (Red Cut) wird benötigt für um set_difference für das Element HL1 zu binden.

  
