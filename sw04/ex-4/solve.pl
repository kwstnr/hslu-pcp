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

grandmother(X, Y) :-
  mother(X, Z),
  parent(Z, Y).

grandfather(X, Y) :-
  father(X, Z),
  parent(Z, Y).

% alle grossmütter von jim => grandmother(X, jim).

offspring(X, Y) :- parent(Y, X).
offspring(X, Y) :-
  parent(Y, Z),
  parent(Z, X).

offspring2(X, Y) :- parent(Y, X).
offspring2(X, Y) :- parent(Y, Z), offspring2(X, Z).


?- use_module(library(clpfd)).

sudoku_local(Rows) :-
  append(Rows, Vs), Vs ins 1..9,
  maplist(all_distinct, Rows),
  transpose(Rows, Columns),
  maplist(all_distinct, Columns),
  Rows = [A,B,C,D,E,F,G,H,I],
  blocks(A, B, C), blocks(D, E, F), blocks(G, H, I),
  maplist(label, Rows).

blocks([], [], []).
blocks([A,B,C|Bs1], [D,E,F|Bs2], [G,H,I|Bs3]) :-
  all_distinct([A,B,C,D,E,F,G,H,I]),
  blocks(Bs1, Bs2, Bs3).

replace_0([], []).
replace_0([0 | T], [_ | R]) :-
  replace_0(T, R).
replace_0([H | T], [H | R]) :-
  H \= 0,
  replace_0(T, R).
  
?- use_module(library(http/http_client)).
?- use_module(library(http/http_json)).

get_problem(Problem, Id, Reply) :- 
  string_concat('http://localhost:16316/problem/', Problem, BaseUrl),
  string_concat(BaseUrl, '/', BaseUrl2),
  string_concat(BaseUrl2, Id, Url),
  http_get(Url, Reply, [json_object(dict)]).

send_solution(Problem, Id, Solution, Reply) :-
  string_concat('http://localhost:16316/problem/', Problem, Url),
  http_post(Url, json(json([solution=Solution, id=Id])), Reply, []).

relationship(JSONReply, Solution) :-
  atom_string(FirstPerson, JSONReply.firstPerson),
  atom_string(SecondPerson, JSONReply.secondPerson),
  atom_string(Relationship, JSONReply.relationship),
  (call(Relationship, FirstPerson, SecondPerson) -> Solution = "true" ; Solution = "false").

sudoku(JSONReply, Solution) :-
  Problem = JSONReply.sudoku,
  maplist(replace_0, Problem, Solution),
  Solution = [A,B,C,D,E,F,G,H,I],
  sudoku_local([A,B,C,D,E,F,G,H,I]).


solve_problem(Problem, Reply, Solution) :-
  call(Problem, Reply, Solution).

solve(Problem, Id) :-
  get_problem(Problem, Id, Reply),
  solve_problem(Problem, Reply, Solution),
  send_solution(Problem, Id, Solution, _).

