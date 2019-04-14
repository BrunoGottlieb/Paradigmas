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

genRectsInLine :: Int -> [Rect] --Usado no caso 1
genRectsInLine n  = [((m*(w+gap),t*(w+(gap*4))),w,h) | m <- [0..fromIntegral (n-1)], t <- [0..fromIntegral (4)]]
  where (w,h) = (50,50)
        gap = 10

genCircles :: Int -> Float -> Float -> Float -> [Circle] -- Usado no caso 2
genCircles n spc x y = [((x + spc * sin((i * graus) * rad), y + spc * cos((graus * i) * rad)), r) | i <- [0..fromIntegral(n-1)]]
  where r = 40 -- raio
        graus = fromIntegral(360) / fromIntegral(n) -- deslocamento dos circulos
        rad = pi / fromIntegral(180) -- divisão para obter angulo em radianos
        -- 1 grau equivale a pi / 180
        -- Multiplicando qualquer quantia por pi / 180 obtem-se o valor em radianos

gen3Circles :: Int -> Float -> Int -> [Circle] -- Usado no caso 3
gen3Circles n r x
    | x == 0 = [((x*100,y*100),r) | x <- [2.0,4.0..(fromIntegral(n)*2)], y <- [1.0, 3.0]]
    | x == 1 = [((x*100,y*100),r) | x <- [2.3, 4.3..(fromIntegral(n)*2)], y <- [1.5, 3.5]]
    | x == 2 = [((x*100,y*100),r) | x <- [2.6, 4.6..(fromIntegral(n)*2)], y <- [1.0, 3.0]]

genSenoide :: Int -> Float -> [Circle] -- Usado no caso 4
genSenoide n h = [((margem_x + x*10, pos_y + h * cos((graus + x*10) * rad)), r) | x <- [1.0,4.0..fromIntegral(n)]]
  where r = 20 -- tamanho do raio dos círculos
        graus = fromIntegral(360) / fromIntegral(n) -- deslocamento dos circulos
        rad = pi / fromIntegral(180) -- divisão para obter angulo em radianos
        pos_y = 200
        margem_x = 100

genCirclesExtra :: Int -> Float -> Int -> [Circle] --Usado no caso 5
genCirclesExtra c r i
    | i == 0 = [((x*100,y*100),r) | x <- [1.0,2.0..fromIntegral(n)], y <- [1.0,2.0..fromIntegral(c)]]
    | i == 1 = [((x*100,y*100),r) | x <- [1.5, 2.5..fromIntegral(c-1)], y <- [1.5, 2.5..fromIntegral(c-1)]]
    where n = c * c
-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------
svgCircle :: Circle -> String -> String
svgCircle ((x,y),r) style =
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />\n" x y r style

-- Gera string representando retângulo SVG
-- dadas coordenadas e dimensoes do retângulo e uma string com atributos de estilo
svgRect :: Rect -> String -> String
svgRect ((x,y),w,h) style =
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

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

case1 :: IO ()
case1 = do
  writeFile "case1.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRectsInLine (nrects*5-1)
        palette = greenPalette (nrects*5-1)
        nrects = 10 --Numero de colunas de quadrados
        (w,h) = (1500,500) -- width,height da imagem SVG

case2 :: IO ()
case2 = do
  writeFile "case2.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = genCircles nCircles espacamento x y
        palette = palette2 nCircles
        nCircles = 12 -- numero de circulos
        espacamento = 175 -- Tamanho do circulo grande
        x = 500 -- posicao x do circulo
        y = 250 -- posicao y do circulo
        (w,h) = (1500,500) -- width,height da imagem SVG

case3 :: IO ()
case3 = do
  writeFile "case3.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ circfigs ++ circfigs2 ++ circfigs3 ++ svgEnd
        circfigs = svgElements svgCircle (gen3Circles ncircles (r_circle-1) 0) (map svgStyle red)
        circfigs2 = svgElements svgCircle (gen3Circles ncircles (r_circle-1) 1) (map svgStyle green)
        circfigs3 = svgElements svgCircle (gen3Circles ncircles (r_circle-1) 2) (map svgStyle blue)
        red = mixPalette (ncircles-1) 0
        green = mixPalette (ncircles-1) 1
        blue = mixPalette (ncircles-1) 2
        ncircles = 6 --Numero de figuras
        r_circle = 50 --Raio das figuras
        (w,h) = (1500,700) -- width,height da imagem SVG

case4 :: IO ()
case4 = do
  writeFile "case4.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = genSenoide nCircles amplitude
        palette = palette2 nCircles
        nCircles = 80 -- numero de circulos
        amplitude = 80 -- amplitude da onda
        (w,h) = (1500,500) -- width,height da imagem SVG

case5 :: IO ()
case5 = do
  writeFile "case5.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ circfigs ++ circfigs2 ++ svgEnd
        circfigs = svgElements svgCircle (genCirclesExtra ncolunas (r_circle-1) 0) (map svgStyle red)
        circfigs2 = svgElements svgCircle (genCirclesExtra ncolunas (r_circle-1) 1) (map svgStyle green)
        red = mixPalette (ncolunas*ncolunas-1) 0
        green = mixPalette (ncolunas*ncolunas-1) 1
        ncolunas = 5 --Numero de colunas dos circulos
        r_circle = 50 --Raio das figuras
        (w,h) = (1500,700) -- width,height da imagem SVG
