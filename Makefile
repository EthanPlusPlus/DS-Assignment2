# binary search program makefile

JFLAGS = -g
JC = javac
SRCDIR= src
BINDIR= bin
DOCDIR = doc
DATADIR = data

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) -cp $(BINDIR) $*.java -d $(BINDIR)

CLASSES = $(SRCDIR)/*.java

default: classes run javadoc

classes: 
	$(JC) $(JFLAGS) -cp $(BINDIR) $(CLASSES) -d $(BINDIR)

clean:
	$(RM) $(BINDIR)/*.class

run:
	java -cp $(BINDIR) Main > output.txt
	tail -n 60 output.txt > instruments.txt
	python3 $(SRCDIR)/graphs.py

javadoc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java

docs:
	javadoc -d doc/ src/*.java