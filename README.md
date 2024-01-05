## Информация о проекте
Необходимо организовать систему учета для питомника, в котором живут
домашние и вьючные животные.

## Задания
1. Используя команду cat в терминале операционной системы Linux, создать
два файла Домашние животные (заполнив файл собаками, кошками,
хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и
ослы), а затем объединить их. Просмотреть содержимое созданного файла.
Переименовать файл, дав ему новое имя (Друзья человека).
```
cd ~
mkdir Sandbox
cd Sandbox
cat > pets
собаки
кошки
хомяки
^C
cat > pack_animals
лошади
верблюды
ослы
^C
ll
cat pets pack_animals > animals
ll
cat animals
mv animals mans_friends
ll
```

2. Создать директорию, переместить файл туда.
```
cd ~
mkdir Sandbox2
cd Sandbox
mv mans_friends ~/Sandbox2
cd ~/Sandbox2
ll
```

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
```
sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.23-1_all.deb
sudo dpkg -i mysql-apt-config_0.8.23-1_all.deb
sudo apt update
sudo apt install mysql-server
```

4. Установить и удалить deb-пакет с помощью dpkg.
```
sudo wget https://download.docker.com/linux/ubuntu/dists/jammy/pool/stable/amd64/docker-ce-cli_20.10.13~3-0~ubuntu-jammy_amd64.deb
sudo dpkg -i docker-ce-cli_20.10.133-0ubuntu-jammy_amd64.deb
sudo dpkg -r docker-ce-cli
```

5. Выложить [историю команд](https://github.com/Shchelokoff/FCW_Specialization/blob/main/Ubuntu_sripts.txt) в терминале ubuntu

6. Нарисовать [диаграмму](https://github.com/Shchelokoff/FCW_Specialization/blob/main/Diagramm_extends.png), в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).

7. В подключенном MySQL репозитории создать [базу данных](https://github.com/Shchelokoff/FCW_Specialization/blob/main/Database.sql)“Друзья человека”
```sql
CREATE DATABASE Human_friends;
```

8. Создать таблицы с иерархией из диаграммы в БД.
Также необходимо создать таблицу pet_list для дополнительного
взаимодействия Intellij Idea вместе с DBeaver
```sql
USE Human_friends;

DROP TABLE IF EXISTS pet_list;
CREATE TABLE pet_list 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    PetName VARCHAR(20), 
    Birthday DATE,
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS animal_classes;
CREATE TABLE animal_classes
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Class_name VARCHAR(20)
);

INSERT INTO animal_classes (Class_name)
    VALUES ('вьючные'), ('домашние');  

DROP TABLE IF EXISTS packed_animals;
CREATE TABLE packed_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO packed_animals (Genus_name, Class_id)
    VALUES ('Лошади', 1), ('Ослы', 1), ('Верблюды', 1); 
    
DROP TABLE IF EXISTS home_animals;
CREATE TABLE home_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO home_animals (Genus_name, Class_id)
    VALUES ('Кошки', 2), ('Собаки', 2), ('Хомяки', 2); 

DROP TABLE IF EXISTS cats;
CREATE TABLE cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
```
9. Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения
```sql
INSERT INTO cats (Name, Birthday, Commands, Genus_id)
    VALUES ('Борис', '2000-01-01', 'Где вискас Борис?', 1), 
    ('Феликс', '2000-02-01', "Кыса-кыса", 1),  
    ('Клим', '2000-03-01', "Клим Саныч!", 1); 

DROP TABLE IF EXISTS dogs;
CREATE TABLE dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO dogs (Name, Birthday, Commands, Genus_id)
    VALUES ('Барон', '2000-01-01', 'ко мне, лежать, лапу, голос', 2),
    ('Граф', '2000-06-12', "сидеть, лежать, лапу", 2),  
    ('Шарик', '2000-05-01', "сидеть, лежать, лапу, след, фас", 2), 
    ('Босс', '2000-05-10', "сидеть, лежать, фу, место", 2);

DROP TABLE IF EXISTS hamsters;
CREATE TABLE hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO hamsters (Name, Birthday, Commands, Genus_id)
    VALUES ('Малой', '2000-10-12', '', 3),
    ('Медведь', '2000-03-12', "атака сверху", 3),  
    ('Ниндзя', '2000-07-11', NULL, 3), 
    ('Бурый', '2000-05-10', NULL, 3);

DROP TABLE IF EXISTS horses; 
CREATE TABLE horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES packed_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO horses (Name, Birthday, Commands, Genus_id)
    VALUES ('Броня', '2000-01-12', 'бегом, шагом', 1),

DROP TABLE IF EXISTS donkeys;
CREATE TABLE donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES packed_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO donkeys (Name, Birthday, Commands, Genus_id)
    VALUES ('Осёл', '2000-04-10', NULL, 2),
    ('Шрек', '2000-03-12', "", 2),  
    ('Фиона', '2000-07-12', "", 2), 
    ('Фаркуад', '2000-12-10', NULL, 2);

DROP TABLE IF EXISTS camels;
CREATE TABLE camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES packed_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO camels (Name, Birthday, Commands, Genus_id)
    VALUES ('Жора', '2000-04-10', 'вернись', 3),
    ('Сеня', '2000-03-12', 'остановись', 3),  
    ('Ишак', '2000-07-12', 'повернись', 3), 
```

10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
```sql
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;

SELECT Name, Birthday, Commands FROM horses
UNION SELECT  Name, Birthday, Commands FROM donkeys;
```

11. Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице
```sql
CREATE TEMPORARY TABLE animals AS SELECT *, 'Лошади' as genus 
FROM horses
UNION SELECT *, 'Ослы' AS genus FROM donkeys
UNION SELECT *, 'Собаки' AS genus FROM dogs
UNION SELECT *, 'Кошки' AS genus FROM cats
UNION SELECT *, 'Хомяки' AS genus FROM hamsters;

CREATE TABLE yang_animal AS
SELECT Name, Birthday, Commands, genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM animals 
WHERE Birthday 
BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM yang_animal;
```
12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.
```sql
SELECT h.Name, h.Birthday, h.Commands, pa.Genus_name, ya.Age_in_month 
FROM horses h
LEFT JOIN yang_animal ya ON ya.Name = h.Name
LEFT JOIN packed_animals pa ON pa.Id = h.Genus_id
UNION 
SELECT d.Name, d.Birthday, d.Commands, pa.Genus_name, ya.Age_in_month 
FROM donkeys d 
LEFT JOIN yang_animal ya ON ya.Name = d.Name
LEFT JOIN packed_animals pa ON pa.Id = d.Genus_id
UNION
SELECT c.Name, c.Birthday, c.Commands, ha.Genus_name, ya.Age_in_month 
FROM cats c
LEFT JOIN yang_animal ya ON ya.Name = c.Name
LEFT JOIN home_animals ha ON ha.Id = c.Genus_id
UNION
SELECT d.Name, d.Birthday, d.Commands, ha.Genus_name, ya.Age_in_month 
FROM dogs d
LEFT JOIN yang_animal ya ON ya.Name = d.Name
LEFT JOIN home_animals ha ON ha.Id = d.Genus_id
UNION
SELECT hm.Name, hm.Birthday, hm.Commands, ha.Genus_name, ya.Age_in_month 
FROM hamsters hm
LEFT JOIN yang_animal ya ON ya.Name = hm.Name
LEFT JOIN home_animals ha ON ha.Id = hm.Genus_id;
```
13. Создать [класс с Инкапсуляцией методов и наследованием по диаграмме](https://github.com/Shchelokoff/FCW_Specialization/tree/main/src/model).

14. Написать [программу, имитирующую работу реестра домашних животных](https://github.com/Shchelokoff/FCW_Specialization/tree/main/src).
В программе должен быть реализован следующий функционал:    
14.1 Завести новое животное    
14.2 определять животное в правильный класс    
14.3 увидеть список команд, которое выполняет животное    
14.4 обучить животное новым командам    
14.5 Реализовать навигацию по меню
