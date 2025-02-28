n(red, green).		n(red, yellow).
n(green, red).		n(green, yellow).
n(yellow, red).		n(yellow, green).

colors(LU, NW, OW, SZ, UR, ZG) :-
	UR = yellow,
  SZ = red,
  n(LU, ZG), n(LU, NW), n(LU, OW), n(LU, SZ),
  n(NW, OW), n(NW, UR), n(NW, SZ),
  n(OW, UR),
  n(UR, SZ),
  n(SZ, ZG).

% colors(LU, NW, OW, SZ, UR, ZG).
