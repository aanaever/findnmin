# Сервис для поиска N-го минимального числа в файле

## Сборка и запуск: 
Проект можно запустить 2-мя способами:
----------

### Docker

#### 1. Склонируйте репозиторий:

```bash
git clone https://github.com/aanaever/findnmin.git
cd your-repo
```
#### 2. Соберите Docker-образ:

```bash
docker build -t excel-api .
```

#### 3. Запустите контейнер:

```bash
docker build -t excel-api .
```
### filePath 
Приложение запущенное через Docker контейнер по умолчанию не видит
локальные файлы.
Необходимо дать доступ к файлам или запустить контейнер так - указав папку в которой лежит файл:
```bash
docker run -p 9090:9090 \
  -v /Users/name/Documents:/data \
  excel-api
```

И используйте путь в в таком виде: /data/test.xlsx

### Локальный запуск 

#### Технологии
- Java 17
- Gradle

#### 1. Склонируйте репозиторий:

```bash
git clone https://github.com/aanaever/findnmin.git
cd your-repo
```

#### 2. Соберите проект:

```bash
./gradlew build
```
#### 3. Запустите приложение:

```bash
./gradlew bootRun
```

## Использование

1. Откройте Swagger UI: http://localhost:9090/swagger-ui/index.html#/
2. Перейдите по эндпоинту POST /api/find-n-min
3. Укажите путь к файлу .xlsx 
4. Укажите значение N

## Детали

- Используется алгоритм QuickSelect
- Алгоритмическая сложность O(n)
