PROJECT1NAME=Lexer
PROJECT2NAME=project2
TRIEFILE=Trie
TESTFILE=toy.txt
PARSERNAME=Parser
CUPPATH=cup/src/java
PROJECT1CLEANUPFILES=$(PROJECT1NAME).java Main.java *.class
PROJECT2CLEANUPFILES=$(PARSERNAME).java sym.java 

main : $(PROJECT1NAME).class $(PARSERNAME).class
	@echo Done

$(PROJECT1NAME).class : $(PROJECT1NAME).lex sym.class
	@echo Generating java code...
	@java JLex.Main $(PROJECT1NAME).lex
	@echo -n Renaming output file...
	@mv $(PROJECT1NAME).lex.java $(PROJECT1NAME).java
	@echo done
	@echo -n Compiling $(PROJECT1NAME)...
	@javac -Xlint:none -cp $(CUPPATH):. $(PROJECT1NAME).java
	@echo done

$(TRIEFILE).class : $(TRIEFILE).java
	@echo -n Compiling $(TRIEFILE)...
	@javac $(TRIEFILE).java
	@echo done

$(PARSERNAME).class : $(PROJECT2NAME).cup
	@echo -n Compiling parser...
	@javac -cp $(CUPPATH):. $(PARSERNAME).java
	@echo done

sym.class : $(PROJECT2NAME).cup
	@echo Generating parser file...
	@java -cp $(CUPPATH):. java_cup.Main -interface  -parser $(PARSERNAME) $(PROJECT2NAME).cup
	@javac sym.java

$(PROJECT2NAME).class : $(PARSERNAME).class
	@echo -n Compiling project 2...
	@javac -cp $(CUPPATH):. $(PROJECT2NAME).java
	@echo done
	
run :
	@make --no-print-directory
	@echo Running $(PARSERNAME)...
	@echo
	@java -cp $(CUPPATH):. $(PARSERNAME)

clean :
	@echo -n Cleaning lexical analyzer files...
	@rm -rf $(PROJECT1CLEANUPFILES)
	@echo done
	@echo -n Cleaning syntax analyzer files...
	@rm -rf $(PROJECT2CLEANUPFILES)
	@echo done
