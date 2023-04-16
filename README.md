# WebSocket2
WebSocket with php BackEnd

http://socketo.me/docs/install
https://getcomposer.org/download/

1 - create folder BackEnd and cd BackEnd

2 - php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"

3 - php -r "if (hash_file('sha384', 'composer-setup.php') === '55ce33d7678c5a611085589f1f3ddf8b3c52d662cd01d4ba75c0ee0459970c2200a51f492d557530c71c15d8dba01eae') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;"

4 - php composer-setup.php

5 - php -r "unlink('composer-setup.php');"

6 - php composer.phar require cboden/ratchet

7 - open the BackEnd Folder and Edit composer.json

8 - go to http://socketo.me/docs/hello-world and edit with it

9 - php composer.phar update

10 - create folder bin and src

11 - create file server.php into bin

12 - create file chat.php into src

13 - complete the code by http://socketo.me/docs/hello-world

14 - cd to bin

15 - php server.php

16 - get system ip by cmd/ipconfig

17 - the websocket link is = "ws://192.168.1.101:3000"
