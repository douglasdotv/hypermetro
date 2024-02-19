# Hypermetro

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/hypermetro/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/hypermetro/blob/master/README.pt-br.md)

Esta aplicação simula a navegação em um sistema de metrô utilizando um grafo como estrutura de dados e o algoritmo de Dijkstra para encontrar rotas. Os dados de linhas de metrô e estações são lidos de um arquivo JSON, e os usuários podem realizar operações como adicionar ou remover estações (vértices), conectar diferentes linhas e encontrar as rotas mais curtas ou mais rápidas entre as estações via CLI. Serve principalmente como uma ferramenta prática para entender a aplicação de grafos no desenvolvimento de software. A biblioteca Gson foi utilizada para a leitura de arquivos JSON, e o design pattern "strategy" foi empregado para lidar facilmente com os comandos disponíveis.
