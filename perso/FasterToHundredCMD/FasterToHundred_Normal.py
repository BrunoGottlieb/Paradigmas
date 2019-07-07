# SOBRE:
# Apenas a titulo de comparacao, essa eh a primeira versao que eu havia feito, sem orientacao a objeto, apenas para entender a linguagem

from random import randint
class Main:

	caixas = [0,0,0,0,0]
	totalScore = 0
	turno = 0

	def generate(caixas, q):
		for i in range(int(q)):
			caixas[i] = randint(0,9)

	def printCaixas(caixas):
		print(caixas[0], caixas[1], caixas[2], caixas[3], caixas[4])

	print("\nCaixas:")
	for i in range(5):
		caixas[i] = randint(0,9)

	while True:

		printCaixas(caixas)

		turno+= 1

		print("\ncomecando turno: " + str(turno))

		x = input("\nSelecione uma caixa: ")

		valorEscolhido = caixas[int(int(x)-int(1))]

		print("\nCaixa escolhida possui o valor: " + str(valorEscolhido))

		caixas[int(int(x)-int(1))] = 0

		totalScore += valorEscolhido

		print("\nTotal score agora eh: " + str(totalScore))

		printCaixas(caixas)

		x = input("\nEscolha quantas caixas gerar: ")
		qtd = x
		print("\nQuantia de caixas a serem geradas: " + str(qtd))

		totalScore -= int(qtd)

		print("\nTotal score agora eh: " + str(totalScore))

		generate(caixas, qtd)

		if(totalScore >= 100):
			break

	print("\nAcabou o jogo em " + str(turno) + " turnos")
