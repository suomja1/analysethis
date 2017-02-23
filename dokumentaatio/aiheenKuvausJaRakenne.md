### Aihemäärittely
*Aihe*: Yksinkertainen DNA-analysaattori. Toteutetaan DNA:n analysointiohjelma, jolla käyttäjä pystyy laskemaan  (tilastollisia) tunnuslukuja syöttämällensä DNA-sekvenssille. Ohjelma tarjoaa myös mahdollisuuden kahden sekvenssin vertailuun ja analyysin tuloksen tulostamiseen.

*Käyttäjät*: Severi Sekvensoija.

*Toiminnot*: 
- analysaattorin käynnistäminen ja sammuttaminen
- DNA-sekvenssin syöttäminen
 - käsin
 - tiedostosta
 - satunnainen sekvenssi
- sekvenssin analysointi
 - emästen lukumäärä, tuntemattomat emäkset
 - frekvenssit ja suhteelliset frekvenssit
   - emäs, kodoni
 - guaniinin ja  sytosiinin suhteellinen osuus ([GC-pitoisuus](https://en.wikipedia.org/wiki/GC-content))
- kahden sekvenssin vertailu 
 - [Levenšteinin etäisyys](https://en.wikipedia.org/wiki/Levenshtein_distance)
- edustaako sekvenssi ominaisuuksiltaan proteiinia koodaavaa sekvenssiä?
- sekvenssin käänteistoiston muodostaminen / muokkaaminen
- analyysin tuloksen
 - lukeminen
 - tulostaminen tiedostoon

*Jatkokehitys*:
- sekvenssin analysointi
 - toistuvat jaksot ([toistojaksot](https://en.wikipedia.org/wiki/Repeated_sequence_(DNA)))
- kahden sekvenssin vertailu 
 - [Damerau–Levenšteinin etäisyys](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance)
- aloitus- ja lopetuskodonien tunnistaminen sekvenssin sisältä

### Rakenne

*Määrittelyvaiheen luokkakaavio*: Luokkakaavioon on merkitty järjestelmän tärkeimmät luokat.

![luokkakaavio](luokkakaavio.png)

*Rakennekuvaus*: TODO

*Käyttötapauksien sekvenssikaavioita*

![sekvenssikaavio1](DNA-sekvenssianalysaattori merkkijonosta.png)

![sekvenssikaavio2](DNA-sekvenssi suhteellinen GC-massa.png)

![sekvenssikaavio3](Aloitus ja lopetuskodonien tarkastus.png)

Tähän tulee päivittynyt versio yllä olevasta!
