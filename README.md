# learnupMaven2

## ДЗ 24

<p>Создайте новый репо. Реализуйте класс, который будет личным кабинетом предпринимателя в налоговой. Он будет хранить доходы и расходы. Создайте по методу для увеличения каждого из этих двух показателей (предусмотрите некорректный ввод). Создайте два метода (по одному на каждый тип налогооблажения): один по расчёту налогов как 6% от доходов, другой считает налоги как 15% от разницы между доходами и расходами; разумеется, налоги не могут быть отрицательными.</p>

<p>Подключите JaCoCo в режиме обрушения сборки при недостаточном покрытии (не 100%).</p>

## ДЗ 25

<p>Создайте менеджер шагометра с методом `add(int day, int steps)`, который не перезаписывает значение за день, а добавляет к уже текущему значению. Пусть менеджер возвращает число, равное количеству шагов, которое надо сделать в этот день чтобы обновить превысить текущий максимум шагов за день.</p>

## ДЗ 26

<p>Возьмите шагомер из прошлого задания. Сделайте класс StepBattle, который принимает два шагомера и имеет методы:</p>

<p>`addSteps(int player, int day, int steps)`, который добавляет шаги в первый или второй шагомер (номер определяется первым параметром, либо 1, либо 2).</p>

<p>`int winner()`, возвращающий номер выигравшего игрока - тот, у кого больше всех было шагов в сумме.</p>

<p>Протестируйте этот менеджер с помощью мокито.</p>

## ДЗ 27

<p>Добавьте возможность шагомерам сравниваться между собой. Сделайте через Comparable сравнение по сумме шагов, а через Comparator сравнение по количеству дней, в которых шагов больше минимума (минимум должен задаваться в конструкторе компаратора).</p>

<p>В тестах покажите что всё работает (можно на примере сортировки, можно на примерах ассертов на методы compareTo и compare).</p>

## ДЗ 28

<p>Добавьте в шагомер метод `Stream < Integer > getAllAbove(int steps)`, который возвращает стрим из всех дней, когда было больше чем steps шагов. </p>

## ДЗ 29

<p>Добавьте в шагомер проверку на корректность дня (пусть это будет порядковый номер дня в году от 1 до 365, без високосных) и шагов (положительное число). Если некорректно - выкидываем исключения ваших собственных типов - IllegalDayException, IllegalStepsException.</p>

