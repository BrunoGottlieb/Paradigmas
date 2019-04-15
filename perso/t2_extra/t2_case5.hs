--Bruno Gottlieb CC

import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)


-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,80 + i * 10,0) | i <- [0..n]]

palette2 :: Int -> [(Int,Int,Int)]
palette2 n = [if rem i 3 == 0 then (255, 0, 0) else if rem i 3 == 1 then (0, 255, 0) else (0, 0, 255)| i <- [0..n]]

mixPalette :: Int -> Int -> [(Int,Int,Int)]
mixPalette n x
    | x == 0 = [(255,i*0,0) | i <- [0..n]] --red
    | x == 1 = [(0*i,255,0) | i <- [0..n]] --green
    | x == 2 = [(0*i,0,255) | i <- [0..n]] --blue

-------------------------------------------------------------------------------
-- Geração de figuras em suas posições
-------------------------------------------------------------------------------

genCirclesExtra :: Int -> Float -> Int -> [Circle] --Usado no caso 5
genCirclesExtra c r i
    | i == 0 = [((x*100,y*100),r) | x <- [1.0,2.0..fromIntegral(n)], y <- [1.0,2.0..fromIntegral(c)]]
    | i == 1 = [((x*100,y*100),r) | x <- [1.5, 2.5..fromIntegral(c-1)], y <- [1.5, 2.5..fromIntegral(c-1)]]
    | i == 2 = [((500 + x*100,y*100-50),r) | x <- [1.5, 2.5..fromIntegral(c-1)], y <- [1.5, 2.5..fromIntegral(c-1)]]
    where n = c * c
-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------
svgCircle :: Circle -> String -> String
svgCircle ((x,y),r) style =
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />\n" x y r style

-- String inicial do SVG
svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h

-- String final do SVG
svgEnd :: String
svgEnd = "</svg>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles

-------------------------------------------------------------------------------
-- Função principal que gera arquivo com imagem SVG
-------------------------------------------------------------------------------

case5 :: IO ()
case5 = do
  writeFile "case5.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ circfigs ++ circfigs2 ++ circfigs3 ++ svgEnd
        circfigs = svgElements svgCircle (genCirclesExtra ncolunas (r_circle) 0) (map svgStyle red)
        circfigs2 = svgElements svgCircle (genCirclesExtra ncolunas (r_circle) 1) (map svgStyle green)
        circfigs3 = svgElements svgCircle (genCirclesExtra (ncolunas+1) (r_circle) 2) (map svgStyle extra)
        red = mixPalette (ncolunas*ncolunas-1) 0
        green = mixPalette (ncolunas*ncolunas-1) 1
        extra = palette2 (ncolunas*ncolunas)
        ncolunas = 5 --Numero de colunas dos circulos
        r_circle = 50 --Raio das figuras
        (w,h) = (1500,700) -- width,height da imagem SVG
