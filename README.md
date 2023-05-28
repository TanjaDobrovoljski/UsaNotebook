# UsaNotebook

Napisati Android aplikaciju koja predstavlja geografski priručnik za proizvoljnu
državu. Aplikacija se sastoji iz minimalno sljedećih kategorija:
• Gradovi
• Znamenitosti
• Novosti
• Uopšteno
• Podešavanja
Gradovi – dio aplikacije u kojem se prikazuje lista sa minimalno deset gradova. Za
svaki grad je moguće vidjeti detaljnije informacije, koje podrazumijevaju tekstualni opis (npr.
istorijat, broj stanovnika, geografski položaj i sl.), slike grada (učitavati sa neke javnodostupne web lokacije), kao i video prezentaciju (reprodukovati u okviru aplikacije, sa nekog
od streaming servisa), ukoliko postoji za dati grad. Osim toga, potrebno je omogućiti prikaz
vremenske prognoze za izabrani grad, pri čemu se može iskoristiti neki od postojećih servisa
koji daju informacije o prognozi vremena. Dizajn stranice za vremensku prognozu treba da
bude proizvoljan. Omogućiti prikaz svih gradova na mapi.
Znamenitosti – dio aplikacije u kojem se prikazuje lista glavnih znamenitosti i
atrakcija izabrane države. Omogućiti korisniku da neku stavku označi kao „omiljenu“, pri
čemu se ona dodaje u listu omiljenih (favorites). Osim toga, aplikacija treba da omogući i
prikaz stavki na mapi, pomoću pinova. Detaljima stavke treba omogućiti pristup i preko
odgovarajućeg pina.
Novosti – dio aplikacije u kojem se prikazuje lista novosti vezanih za izabranu državu
koje je potrebno preuzimati od odgovarajućeg servisa. Moguće je iskoristiti gotov servis
(npr. newsapi.org), ili kreirati sopstveni servis na proizvoljan način. Osim preuzimanja
novosti kada je aplikacija u online režimu, potrebno je omogućiti čitanje novosti i u offline
režimu, tako što će se posljednje pročitane novosti keširati na uređaju, na adekvatan način.
Uopšteno – dio aplikacije u kojem se prikazuju osnovne informacije za izabranu
državu, poput istorijata, glavnog grada, zastave i sl.
Podešavanja – omogućiti izbor jezika, podešavanje keširanja, izbor minimalnog broja
slika koji se učitava za neki grad (kada se korisnik nalazi u detaljima grada).
Potrebno je podržati lokalizaciju/internacionalizaciju, pri čemu je potrebno omogućiti
da aplikacija radi minimalno na srpskom i engleskom jeziku. Nije neophodno prevoditi
sadržaj koji se preuzima za udaljenih lokacija.
Sve aktivnosti koje bi mogle uticati na blokiranje glavne niti aplikacije, potrebno je
realizovati asinhrono. Za ovu namjenu potrebno je koristiti klasu AsyncTask, ili neku od
biblioteka koje pružaju ovu funkcionalnost.
Potrebno je generisati grafičke elemente tako da se pokrije minimum pet različitih
aktuelnih gustina displeja kao i različitih dimenzija displeja. Za generisanje grafičkih resursa,
dozvoljeno je korištenje nekog od alata kao što je AndroidAssetStudio. Prilikom izgradnje
grafičkih dijelova aplikacije (Layout Manager-i, View-ovi), potrebno je voditi računa o
performansama aplikacije. Obezbijediti da aplikacija optimalno radi na oba tipa orjentacije
ekrana (portrait i landscape).
Aplikaciju je potrebno testirati na različitim tipovima emulatora koji predstavljaju
uređaje sa različitom gustinom ekrana i različitom veličinom ekrana.
Sve detalje zadatka koji nisu precizno specifikovani realizovati na proizvoljan način.
Detalji dizajna će se ocjenjivati sa aspekta poštovanja osnovnih smjernica koje Android
predlaže kada je u pitanju izgradnja korisničkog interfejsa, pri čemu je GUI potrebno
implementirati u skladu sa posljednjim trendovima i preporukama (Material Design i sl.).
Potrebno je voditi računa da GUI bude minimalistički i da se izabere odgovarajući skup
srodnih boja kako bi se postigao što bolji korisnički doživljaj (UX). Sve stilske elemente
potrebno je definisati kroz odvojene stilove i teme kako bi se postigla što veća fleksibilnost i
modularnost koda.

