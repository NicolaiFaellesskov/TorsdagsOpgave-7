# Torsdagsopgave - Supermarked
Torsdagsopgave om datastrukturer

Du skal lave et kasseapperat der kan udskrive en pæn kassebon.

I `data` mappen ligger der to csv-filer - `varer.csv` og `tilbud.csv` - de er stort set ens, indeholder følgende data om hver vare:

- varenummer (EAN nummer)
- navn
- dato for sidste prisændring
- pris
- antal styk/gram/ml
- enhed, altså styk, gram, ml, eller andet

og nogle andre værdier som måske kan være af relevans.

`tilbud.csv` indeholder nogle af de samme varenumre, men kun såfremt varen er på tilbud for tiden. Og så vil pris (og dato for prisændring) være en anden.

## Din opgave

Du skal skrive kode der indlæser de mange varer, til nogle `Vare` objekter og gemmer dem i nogle Collections. For eksempel med `alleVarer` og `tilbudsVarer`. 

Så skal du lave kode til et kasseapparat der modtager en tredje Collection - en kurvfuld af blandede varer, som en kunde har samlet sammen i butikken. Vel at bemærke en kunde der ikke er opmærksom på om de er taget fra udvalget af normalpriser, eller tilbudsvarer.

Kasseapparatet skal gøre flere ting:
* Undersøge om varen er på tilbud eller ej, og tage den rigtige pris
* Regne den samlede totalpris ud
* Regne moms-delen af totalprisen ud
* og udskrive en kassebon.

Den kassebon skal være smartere end bare en "dum" liste af alle varer fra kurven!
* Hvis der er købt flere af samme vare, for eksempel to bananer á 2,50 så skal der ikke stå Banan 2,50 to steder på bonen, men derimod være én line hvor der står Banan, efterfulgt af en linje med 2 * 2,50 og så den samlede pris. (Forudsat at bananer altså koster 2,50)
* Hvis der er købt en vare der er på tilbud, skal der ikke kun stå tilbudsprisen, men derimod normalprisen, og så en efterfølgende linje med rabatten, altså hvor meget der er sparet (som et negativt tal)

Og kassebonen skal selvfølgelig først udskrives når alle varer er "scannet", så selv om varerne bliver kastet op på båndet i en stor blanding, så finder kasseapparatet alligevel ud af at samle de varer der måtte være flere af.

For at hjælpe dig lidt med at teste det, er der tilføjet en `Robot.java` kode som du godt må kopiere og bruge i dit projekt - ændr gerne packagenavne til noget der giver mening for dig!

Den robot kan fylde en kurv op med tilfældigt udvalgte varer, nogle gange tager den kun 1 af hver, nogle gange flere, men den blander dem altid fuldstændig vilkårligt.

Opgaven går især ud på at planlægge kodens struktur og udvælge de bedst passende datastrukturer, så der er ikke nærmere fastlagt hvilke packages, klasser og metoder du skal bruge - det er op til dig selv!