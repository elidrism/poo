# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testGUI testBalls testConway testImmigration testSchelling testBoids

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java

testBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBalls.java

testConway:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/conway/TestConway.java

testImmigration:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/immigration/TestImmigration.java

testSchelling:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/schelling/TestSchelling.java

testBoids:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boids/TestBoids.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI

exeBalles:
	java -classpath bin:bin/gui.jar TestBalls

exeConway:
	java -classpath bin:bin/gui.jar conway/TestConway

exeImmigration:
	java -classpath bin:bin/gui.jar immigration/TestImmigration

exeSchelling:
	java -classpath bin:bin/gui.jar schelling/TestSchelling

exeBoids:
	java -classpath bin:bin/gui.jar boids/TestBoids

clean:
	rm -rf bin/*.class
