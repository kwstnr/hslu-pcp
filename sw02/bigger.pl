bigger(elephant, horse).
bigger(horse, dog).
bigger(horse, sheep).

is_bigger(X, Y) :- bigger(X, Y).
is_bigger(X, Y) :- bigger(X, Z), is_bigger(Z, Y).

is_smaller(X, Y) :- is_bigger(Y, X).
