# Macht eine clear Methode auf dem Stack sinn?
Ich finde es ist wieder das gleiche Thema wie in C. Man kann von aussen den Stack clearen indem man pop in einer loop aufruft.
Aus convenience-Gründen macht es aber Sinn.

## Lösung
Auch hier wieder Call by Value. Der Value ist aber eigentlich eine Kopie der Referenz. Die Referenz bezieht sich auf das selbe Objekt.
Hier macht es dementsprechend Sinn um den existierenden Stack zu leeren/clearen.
