# SOBRE:
# Esse codigo eh a versao python cmd orientada a objeto do jogo Faster to Hundred desenvolvido por mim, Bruno Gottlieb, e feito em C# com a Unity
# Eu nunca havia mexido com Python antes, entao fiquei bem contente com o resultado
#
# Recomenda-se ja ter jogado o outro projeto previamente para compreender melhor o funcionamento do jogo, porem, deixarei ainda assim um tutorial aqui:
#
# TUTORIAL:
# Seu objetivo neste jogo eh atingir o score de 100 na menor quantia de turnos possivel
# A cada rodada voce devera escolher uma caixa, o valor desta caixa sera adicionada ao seu score
# Feito isso, devera ser escolhida uma quantia de novas caixas a serem geradas
# O valor de caixas a serem geradas sera descontado de seu score
# O valor a ser escolhido deve sempre ser entre 1 e 5
# 1 representando a primeira caixa e 5 representando a ultima caixa
# Esse ciclo ira se repetir ate que o score objetivo (100) tenha sido atingido

from random import randint
import os
class Caixas:

	def __init__(self, caixas):
		for i in range(5):
			self.caixas = caixas

	def generate(self, q):
		for i in range(int(q)):
			self.caixas[i] = randint(0,9)

	def printCaixas(self):
		print("\nCaixas:")
		print(self.caixas[0], self.caixas[1], self.caixas[2], self.caixas[3], self.caixas[4])

	def getCaixa(self, v):
		return self.caixas[int(int(v)-int(1))]

	def killCaixa(self, v):
		self.caixas[int(int(v)-int(1))] = 0

class Turno:

	def __init__(self, t):
		self.turno = t

	def incrementTurno(self):
		self.turno += 1

	def getTurno(self):
		print(self.turno)

class Score:

	def __init__(self, s):
		self.score = s

	def incrementScore(self, v):
		self.score += int(v)

	def decrementScore(self, v):
		self.score -= int(v)

	def getScore(self):
		print(self.score)
		return(self.score)

	def checkScore(self):
		return(self.score)

class Main:

	caixas = [0,0,0,0,0]

	c = Caixas(caixas)
	t = Turno(0)
	s = Score(0)

	c.generate(5) # Gera as 5 caixas iniciais com valores aleatorios

	def checkValidValue(v): # Confere se o valor digitado eh valido
		if int(v) >= 1 and int(v) <= 5:
			return True
		else:
			print("\nValor precisa ser entre 1 e 5")
			return False

	while True:

		print("\nTotal score: ")
		s.getScore()

		t.incrementTurno()
		print("\nTurno atual: ")
		t.getTurno()

		c.printCaixas() # Printa todas as caixas

		while True:
			x = input("\nSelecione uma caixa: ")
			if checkValidValue(int(x)):
				valor = c.getCaixa(int(x))
				c.killCaixa(int(x))
				break

		s.incrementScore(int(valor)) # Soma o valor da caixa ao total score

		c.printCaixas()

		if s.checkScore() >= 100: # Confere fim de jogo
			break

		while True:
			x = input("\nEscolha a quantia de caixas a serem geradas: ")
			if checkValidValue(int(x)):
				c.generate(int(x))
				break

		s.decrementScore(int(x)) # Diminui a quantia de caixas a serem geradas do total score

		os.system("cls")
		

	print("\nAcabou o jogo.")
	print("\nTurnos gastos:")
	t.getTurno()
