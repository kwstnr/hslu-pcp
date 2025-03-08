warn_cut(T) :- T < 80, write('Temperatur ok'), !.
warn_cut(T) :- T < 100, write('Temperatur sehr warm'), !.
warn_cut(_) :- write('Temperatur zu heiss').

warn_nocut(T) :- T >= 100, write('Temperatur zu heiss').
warn_nocut(T) :- T < 100, T >= 80, write('Temperatur sehr warm').
warn_nocut(T) :- T < 80, write('Temperatur ok').

% Inwiefern ist der Code mit cut (warn_cut/1) eine Optimierung von dem ohne Cut (warn_nocut/1)
% bei warn_nocut müssen redundante checks für T umgesetzt werden.
