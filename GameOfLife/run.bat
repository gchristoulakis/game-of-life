javac -g -cp ./lib/* -d ./bin src/game/of/life/*
cd bin
jar cvfe ../GameOfLife.jar game.of.life.GameWindow game 
jar uvfm ../GameOfLife.jar ../gameoflife.mf
cd ..
jar uvf GameOfLife.jar lib rsc
java -jar GameOfLife.jar
# java -cp .;../lib/* game/of/life/GameWindow