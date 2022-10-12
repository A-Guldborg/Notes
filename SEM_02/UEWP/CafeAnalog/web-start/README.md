# Cafeanalog
For at åbne applikationen kan man enten åbne via sin IDE:
- IntelliJ: I projektstruktur-fanen navigeres til index.html og højreklikkes - Open in - Browser
- VS Code: I projektstruktur-fanen navigeres til index.html og højreklikkes - Open with Live Server 

Alternativt kan køre en web-server gennem sin terminal. Åben en terminal i mappen web-start og kør følgende kommando:

`./node_modules/http-server/bin/http-server -p 8000`

Såfremt dette ikke virker, kan man også bruge følgende kommandoer i command line:

`npm install http-server -g`

`http-server -p 8000`

Herefter kan man åbne hjemmeside i sin foretrukne browser på URL:
http://localhost:8000

OBS: For at gøre brug af log ind og ud funktionen skal man være tilknyttet internettet og benytte sig af localhost.
