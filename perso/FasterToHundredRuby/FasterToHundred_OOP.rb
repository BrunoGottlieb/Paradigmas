# SOBRE:
# Autor: Bruno Gottlieb
# Dessa vez eu resolvi pegar a versao de Python que fiz do meu jogo Faster To Hundred e implementa-la em Ruby para aprender um pouco sobre a linguagem
# A ideia eh bem similar a de Python porem um pouco diferente na forma de implementar. Tentei extrair o maximo de encapsulamento aqui tambem
#
# TUTORIAL:
# Seu objetivo neste jogo eh atingir o score de 100 na menor quantia de turnos possivel
# A cada rodada voce devera escolher uma caixa, o valor desta caixa sera adicionada ao seu score
# Feito isso, devera ser escolhida uma quantia de novas caixas a serem geradas
# O valor de caixas a serem geradas sera descontado de seu score
# O valor a ser escolhido deve sempre ser entre 1 e 5
# 1 representando a primeira caixa e 5 representando a ultima caixa
# Esse ciclo ira se repetir ate que o score objetivo (100) tenha sido atingido

class Caixas

	attr_accessor :caixas # Variavel da classe

	private :caixas # Torna a variavel privada

	def initialize() # Metodo construtor
    	@caixas = [0,0,0,0,0]
	end

	def gerarCaixas(v) # Funcoes sao publicas por padrao
		for i in 0..v.to_i
	    	if i == 5 then
	    		break
	    	end
	    @caixas[i] = rand(0...10)
		end
	end

    def printCaixas
		@caixas
    end

    def getCaixa(v)
    	@caixas[v.to_i]
    end

    def setCaixa(c, v)
    	@caixas[c.to_i] = v.to_i
    end

end

class Turno

	attr_accessor :turno

	private :turno

	def initialize()
    	@turno = 1
	end

	def incrementTurno()
		@turno += 1
	end

	def getTurno()
		@turno
	end

end

class Score

	attr_accessor :score

	private :score

	def initialize()
		@score = 0
	end

	def incrementScore(v)
		@score += v.to_i
	end

	def decrementScore(v)
		@score -= v.to_i
	end

	def getScore()
		@score
	end

end

class Main

	caixas = Caixas.new() # Cria um objeto
	turno = Turno.new()
	score = Score.new()

	def self.checkValidValue(v) # Confere se o valor digitado eh valido
		if v.to_i >= 1 and v.to_i <= 5
			true
		else
			print("\nValor precisa ser entre 1 e 5\n")
			false
		end
	end

	caixas.gerarCaixas(5) # Gera as 5 caixas iniciais

	while true # Laco que ira repetir o jogo

		x = turno.getTurno()
		puts "\nIniciando turno: #{x}"

		x = score.getScore()
		puts "\nScore: #{x}"

		x = caixas.printCaixas()
		puts("\nCaixas:")
		puts("#{x}")

		while true
			print("\nEscolha uma caixa: ")
			valor = gets
			if self.checkValidValue(valor.to_i)
				break
			end
		end

		score.incrementScore(caixas.getCaixa(valor.to_i-1))

		caixas.setCaixa(valor.to_i-1, 0) # Zero a caixa escolhida

		x = caixas.printCaixas()
		puts("\nCaixas:")
		puts("#{x}")

		x = score.getScore()
		puts "\nScore: #{x}"

		while true
			print("\nEscolha a quantia de caixas a serem geradas: ")
			valor = gets
			if self.checkValidValue(valor.to_i)
				break
			end
		end

		caixas.gerarCaixas(valor.to_i-1) # Gero a quantia solicitada

		score.decrementScore(valor) # Retiro o valor escolhido do total score

		turno.incrementTurno()

		Gem.win_platform? ? (system "cls") : (system "clear") # Limpa tela

		if score.getScore > 99 then # Confere fim de jogo
			print "Venceu!"
			x = turno.getTurno()
			print "\nTurnos decorridos: #{x}"
	        break
	    end
	end
end