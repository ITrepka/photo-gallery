# Aplikacja dla fotografa, pozwalająca na utworzenie spersonalizowanej galerii ze zdjęciami dla swoich klientów.

Aby aplikacja uruchomiła się prawidłowo, pobierz zipa z projektem, wypakuj u siebie, otwórz Intellij IDEA w wersji Ultimate, kliknij w lewym górnym rogu File->Open a następnie wejdź do folderu projektu i znajdź plik pom.xml. Zaznacz, następnie ok, a dalej Open as Project.<br>
Przed uruchomieniem aplikacji stwórz bazę danych w MySql Workbench poleceniem: create database photo_gallery_db; <br>
W klasie UserService znajduje się metoda init() z adnotacją PostConstruct, którą należy zakomentować lub usunąć po pierwszym uruchomieniu. Tworzy ona domyślnego Admina:<br>
-login: admin<br>
-hasło: admin
