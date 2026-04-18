# RrjetaKompjuterike

## Pershkrimi i Projektit

Ky projekt implementon nje sistem te thjeshte Client-Server duke perdorur Java dhe TCP sockets.

Serveri mund te pranoje shume kliente njekohesisht dhe i menaxhon ata permes perdorimit te Thread Pool.

---

## Funksionalitetet

### Serveri

- Degjon ne nje IP dhe port te caktuar
- Pranon shume kliente ne te njejten kohe
- Perdor ExecutorService per threads
- Lexon mesazhet nga klientet
- Jep pergjigje sipas rolit:
  - Admin → Qasje e plotë (RWX)
  - User → Qasje vetem per lexim

### Klienti

- Lidhet me serverin
- Dergon nje mesazh (emer/rol)
- Merr pergjigje nga serveri
- Tregon nivelin e qasjes

---

## Autor

Anita Berisha
Arbenit Krasniqi
Kron Pajaziti
