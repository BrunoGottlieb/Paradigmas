-- Bruno Gottlieb
-- Trabalho 1 Paradigmas de Programacao

import Data.Char

-- 1. Verifica se a letra eh uma vogal
isVowel :: Char -> Bool
isVowel x = x `elem` ['a','e','i','o','u','A','E','I','O','U']

-- 2. Adicionar virgula no final de cada string
addComma :: [String] -> [String]
addComma s = map ( ++ ",") s

-- 3. Recebe uma lista de strings e retorna outra lista contendo as strings formatadas como itens de lista em HTML
-- SEM FUNCAO ANONIMA:
htmlListItems :: [String] -> [String]
htmlListItems f = map (htmlList) f

--Subfuncao da htmlListItems
htmlList :: String -> String
htmlList s = "<LI>" ++ s ++ "<LI>"

-- COM FUNCAO ANONIMA:
htmlListItems2 :: [String] -> [String]
htmlListItems2 f = map (\c -> "<LI>" ++ c ++ "<LI>") f

-- 4. Receba uma string e retorna outra sem as vogais
-- SEM FUNCAO ANONIMA:
semVogais :: String -> String
semVogais [] = []
semVogais (x:xs)
  |not(x `elem` "aeiou") = x: semVogais xs
  |otherwise = semVogais xs

-- COM FUNCAO ANONIMA:
semVogais2 :: String -> String
semVogais2 x = filter (\n -> notElem n "aeiouAEIOU") x

-- 5. Receber uma string e retornar todos os caracteres que nao forem espaco como '-'
-- SEM FUNCAO ANONIMA:
codifica :: [Char] -> [Char]
codifica c = if c /= " " then "-" else c

-- COM FUNCAO ANONIMA:
codifica2 :: [Char] -> [Char]
codifica2 xs = [ c | v <- xs , c <- if (v == ' ') then [v] else "-"]

-- 6. Pegar o primeiro nome de uma pessoa em uma string
firstName :: String -> String
firstName s = takeWhile (/= ' ') s

-- 7. Verificar se uma string so contem digitos de 0 a 9
isInt :: String -> Bool
isInt x = length (filter (\n -> not (isDigit n)) x) == 0

-- 8. Obter o ultimo sobrenome de uma pessoa numa string
lastName :: String -> String
lastName x = reverse (firstName (reverse x))

-- 9. Criar usuario da pessoa
userName :: [Char] -> [Char]
userName k = (map (toLower) (userName2 k)) -- Deixar minusculo
-- Funcao auxiliar da questao 9:
userName2 x = [head x] ++ (lastName x) -- Funcao em si

-- 10. Substituir vogais de string
encodeName :: String -> String
encodeName x = map (encodeMe) x
-- Funcao auxiliar da questao 10:
encodeMe :: Char -> Char
encodeMe c    | c == 'a' = '4'
              | c == 'e' = '3'
              | c == 'i' = '2'
              | c == 'o' = '1'
              | c == 'u' = '0'
              | otherwise = c

-- 11. Substituir vogais de string
betterEncodeName  :: String -> String
betterEncodeName x = concatMap (encodeMe2) x
-- Funcao auxiliar da questao 11:
encodeMe2 :: Char -> String
encodeMe2 c
                |  c == 'a' = "4"
                |  c == 'e' = "3"
                |  c == 'i' = "1"
                |  c == 'o' = "0"
                |  c == 'u' = "00"
                | otherwise = [c]

-- 12. Truncar e completar strings
func :: [String] -> [String]
func s = map (func2) s
-- Funcao auxiliar da func:
func2 :: String -> String
func2 n = take 10 (n ++ "..........")











{- ANOTACAO
map (\c -> "-" ++ c ++ "-") ["teste1", "teste2"]
-}
