def inicializarCaixas(caixas)
	for i in 0..5
		if i == 5 then
	      break
	    end
	    caixas[i] = rand(0...10)
	end
end

def printCaixas(caixas)
    for caixa in caixas do
		print(caixa)
	    print(' ')
	end
end

def gerarCaixas(caixas, v)
	for i in 0..v.to_i
	    if i == 5 then
	    	break
	    end
	    caixas[i] = rand(0...10)
	end
end

caixas = [0,0,0,0,0]
totalScore = 0
turno = 1

caixas[4] = 8

inicializarCaixas(caixas)

while true 

	print("\nScore: ")
	print totalScore

	print("\n\nIniciando turno: ")
	print turno

	turno += 1

	puts("\n\nCaixas:")
	printCaixas(caixas)

	print("\n\nEscolha uma caixa: ")
	valor = gets

	print("\nValor escolhido: ")
	print valor

	print("\nConteudo dessa caixa: ")
	print(caixas[(valor.to_i-1)])
	totalScore += caixas[(valor.to_i-1)]
	caixas[(valor.to_i-1)] = 0

	print("\n\nScore: ")
	print totalScore 

	print("\n\nEscolha a quantia de caixas a serem geradas: ")
	valor = gets

	totalScore -= valor.to_i

	gerarCaixas(caixas, valor.to_i-1)

	Gem.win_platform? ? (system "cls") : (system "clear")

	if totalScore > 99 then
		print "\n\nVenceu!"
		print "\nTurnos decorridos: "
		print turno
        break
    end

end
