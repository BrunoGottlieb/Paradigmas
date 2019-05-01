%1. Defina um predicado odd(N) que seja verdadeiro se N for um número ímpar
odd(X) :- 1 is mod(X, 2).

%2. Defina um predicado recursivo hasN(L,N) que seja verdadeiro se L for uma lista de N elementos
hasN([], 0).
hasN([H|T], N) :- hasN(T,X), N is X+1.

%3. Defina um predicado recursivo inc99(L1,L2), de forma que L2 seja uma lista com todos os elementos de L1 acrescidos da constante 99
inc99([],[]).
inc99([H|T],[H2|T2]) :-
    H2 is H+99,
    inc99(T,T2).

%4. Defina um predicado recursivo incN(L1,L2,N), de forma que L2 seja uma lista com todos os elementos de L1 acrescidos da constante N.
incN([],[],_).
incN([H|T],[H2|T2],N) :-
	H2 is H+N,
	incN(T,T2,N).