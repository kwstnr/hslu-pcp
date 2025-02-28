% all females
female(mary). female(liz). female(mia). female(tina). female(ann). female(sue).

% all males
male(mike). male(jack). male(fred). male(tom). male(joe). male(jim).

% all childern of mary
parent(mary, mia). parent(mary, fred). parent(mary, tina).

% all children of mike
parent(mike, mia). parent(mike, fred). parent(mike, tina).

% allchildern of liz
parent(liz, tom). parent(liz, joe).

% all childern of jack
parent(jack, tom). parent(jack, joe).

% all childern of mia
parent(mia, ann).

% all childern of tina
parent(tina, sue). parent(tina, jim).

% all childern of tom
parent(tom, sue). parent(tom, jim).

mother(X, Y) :-
  female(X),
  parent(X, Y).

father(X, Y) :-
  male(X),
  parent(X, Y).

sibling(X, Y) :-
  parent(Z, X),
  parent(Z, Y).
