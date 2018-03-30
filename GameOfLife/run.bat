javac -g -cp ./lib/* -d ./bin src/game/of/life/*
cd bin
jar cvfe ../GameOfLife.jar game.of.life.GameWindow game ../lib
jar uvfm ../GameOfLife.jar ../gameoflife.mf
cd ..
java -jar GameOfLife.jar
# java -cp .;../lib/* game/of/life/GameWindow