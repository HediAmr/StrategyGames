SRC = src/
TEST = test/
DOCS = docs/
CLASSES = classes/
JAR = jar/


COMMONSRC = $(SRC)common/board/Board.java $(SRC)common/board/Cell.java $(SRC)common/board/Generator.java $(SRC)common/board/Ground.java $(SRC)common/board/Ressource.java $(SRC)common/characters/Unit.java $(SRC)common/game/Game.java $(SRC)common/players/Player.java 
WARSRC = $(SRC)war/WarMain.java $(SRC)war/WarMainInput.java $(SRC)war/actions/WarActions.java $(SRC)war/characters/Army.java $(SRC)war/game/WarGame.java $(SRC)war/players/ArmyPlayer.java $(SRC)war/actions/action/Deploy.java
FIELDSRC = $(SRC)field/FieldMain.java $(SRC)field/FieldMainInput.java $(SRC)field/actions/FieldActions.java $(SRC)field/characters/Worker.java $(SRC)field/game/FieldGame.java $(SRC)field/players/WorkerPlayer.java $(SRC)field/actions/action/Deploy.java $(SRC)field/actions/action/DoNothing.java $(SRC)field/actions/action/Exchange.java


all: cls jar doc

cls: warcls fieldcls

jar: guerre.jar agricole.jar warinput.jar fieldinput.jar



guerre.jar: warcls warmanifest
	jar cvfm $(JAR)$@ $(SRC)war/warmanifest -C $(CLASSES) war -C $(CLASSES) common

agricole.jar: fieldcls fieldmanifest
	jar cvfm $(JAR)$@ $(SRC)field/fieldmanifest -C $(CLASSES) field -C $(CLASSES) common

warinput.jar: warcls warmanifestinput
	jar cvfm $(JAR)$@ $(SRC)war/warmanifestinput -C $(CLASSES) war -C $(CLASSES) common

fieldinput.jar: fieldcls fieldmanifestinput
	jar cvfm $(JAR)$@ $(SRC)field/fieldmanifestinput -C $(CLASSES) field -C $(CLASSES) common


warcls: $(COMMONSRC) $(WARSRC)
	javac -d $(CLASSES) $^

fieldcls: $(COMMONSRC) $(FIELDSRC)
	javac -d $(CLASSES) $^


warmanifest:
	echo 'Main-Class: war.WarMain' > $(SRC)war/warmanifest

warmanifestinput:
	echo 'Main-Class: war.WarMainInput' > $(SRC)war/warmanifestinput

fieldmanifest: 
	echo 'Main-Class: field.FieldMain' > $(SRC)field/fieldmanifest

fieldmanifestinput: 
	echo 'Main-Class: field.FieldMainInput' > $(SRC)field/fieldmanifestinput



doc: $(COMMONSRC) $(WARSRC) $(FIELDSRC)
	javadoc -d $(DOCS) $^



clean:
	rm -rf $(DOCS) $(CLASSES) $(JAR)* $(SRC)war/warmanifest $(SRC)field/fieldmanifest $(SRC)war/warmanifestinput $(SRC)field/fieldmanifestinput



.PHONY: all cls jar clean