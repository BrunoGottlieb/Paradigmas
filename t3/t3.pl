%BrunoGottlieb

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

%4. Defina um predicado recursivo incN(L1,L2,N), de forma que L2 seja uma lista com todos os elementos de L1 acrescidos da constante N
incN([],[],_).
incN([H|T],[H2|T2],N) :-
	H2 is H+N,
	incN(T,T2,N).

%5. Defina um predicado recursivo comment(L1,L2), de forma que L1 seja uma lista de strings e L2 tenha todos os elementos de L1 concatenados com o prefixo "%%"
comment([],[]).
comment([H|T],[H2|T2]) :-
	string_concat(H,"%%",H2),
	comment(T,T2).

%6. Defina um predicado recursivo onlyEven(L1,L2), de forma que L2 seja uma lista só com os elementos pares de L1
%onlyEven([],[]).
%onlyEven([H|T],[H2|T2]) :-
	
%7. Defina um predicado recursivo countdown(N,L), de forma que L seja uma lista com os números [N, N-1, N-2, .., 1], sendo N um número positivo
countdown(0,[]).
countdown(N,[H|T]) :-
	N >= 0,
	H is N,
	N2 is H-1,
	countdown(N2,T).