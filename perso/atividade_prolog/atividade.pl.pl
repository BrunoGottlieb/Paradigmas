% 1. Verifica se a letra eh uma vogal
isVowel(C) :- member(C, [a,e,i,o,u]).

% 2. Adicionar virgula no final de cada String
addComma([],[]).
addComma([H|T], [H2|T2]) :-
	string_concat(H,",",H2),
	addComma(T,T2).

% 3. Recebe uma lista de strings e retorna outra lista contendo as strings formatadas como itens de lista em HTML
htmlListItems([],[]).
htmlListItems([H|T],[H2|T2]) :-
	string_concat("<LI>",H,X),
	string_concat(X,"<LI>",H2),
	htmlListItems(T,T2).

% 4. Receba uma string e retorna outra sem as vogais
semVogais([],[]).
semVogais([H|T],[H|T2]) :-
	member(H, [b,c,d,f,g,h,j,k,l,m,n,p,q,r,s,t,v,w,x,y,z]),
	semVogais(T,T2).
semVogais([H|T],L) :-
	member(H, [a,e,i,o,u]),
	semVogais(T,L).

% 5. Essa eh a questao da prova que eu havia me enrolado, agora aqui em casa eu consegui resolver,
%para isso, apenas modifiquei a linha |  N is 1 + N1. |
ocorrencias(_,[],0).
ocorrencias(S, [H|T], N) :-
  S = H,
  ocorrencias(S, T, N1),
  N is 1 + N1.
ocorrencias(S, [H|T], N) :-
  S \= H,
  ocorrencias(S, T, N).
