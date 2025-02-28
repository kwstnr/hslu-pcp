mult(0, _, 0).
mult(_, 0, 0).
mult(1, Y, Y).
mult(X, 1, X).

mult(X, Y, Z) :-
  X > 1, % diese Zeile ist wichtig für die Stack Limit exception, sie gilt als Rekursionsbasis.
  X1 is X-1,
  mult(X1, Y, Z1),
  Z is Z1 + Y.
