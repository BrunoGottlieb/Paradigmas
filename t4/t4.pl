dinheiro(alice, rico).
dinheiro(henrique, rico).
dinheiro(adriano, rico).
dinheiro(caren, rico).
dinheiro(bia, pobre).
dinheiro(pedro, pobre).
dinheiro(maria, pobre).
dinheiro(bernardo, pobre).

insano(adriano).
insano(maria).

arma(maria).
arma(pedro).
arma(henrique).
arma(adriano).
arma(alice).

apartamento(pedro).
apartamento(caren).
apartamento(henrique).
apartamento(bia).
apartamento(adriano).
apartamento(alice).
apartamento(bernardo).
apartamento(maria).

chave(maria).
chave(caren).
chave(henrique).
chave(bia).
chave(alice).

ciumes(alice).
ciumes(caren).

motivo(X) :- dinheiro(X, pobre) ; insano(X) ; ciumes(X).

acesso(X) :- arma(X) , apartamento(X) , chave(X).

assassino(X) :- motivo(X) , acesso(X).