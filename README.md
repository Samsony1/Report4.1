[![Build status](https://ci.appveyor.com/api/projects/status/8p9re3i64g4qgepa?svg=true)](https://ci.appveyor.com/project/Netology-Korolchuk/aqa4-1)

# Домашнее задание к занятию «4.1. Reports»

Для запуска тестируемого приложения скачайте jar-файл из текущего каталога и запускайте его командой: java -jar app-card-delivery.jar Убедиться, что приложение работает, вы можете открыв в браузере страницу: http://localhost:9999

Для формирования отчета:

gradlew clean test allureReport

gradlew allureServe
