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

%8. Defina um predicado recursivo nRandoms(N,L), de forma que L seja uma lista com N números gerados aleatoriamente
nRandoms(0,[]).
nRandoms(N,[H|T]) :-
	random(0,100,H),
	N > 0,
	N2 is N-1,
	nRandoms(N2,T).

%9. Defina um predicado recursivo potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0
potN0(0,[1]).
potN0(N,[H|T]) :-
	N2 is N-1,
	pow(2,N,H),
	potN0(N2, T).

%10. Defina um predicado recursivo zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos de L1 e L2 na mesma posição do elemento de L3
zipmult([],[],[]).
zipmult([H|T],[H1|T1],[H2|T2]) :-
	H2 is H*H1,
	zipmult(T,T1,T2).