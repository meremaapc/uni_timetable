window.onload = function () {
    document.getElementById("a").onclick = function () {
        console.log("A");
        json = httpGet();
        data = JSON.parse(json);

        function compare(a, b) {
            if (a.dayOfWeek.id < b.dayOfWeek.id) {
                return 1
            } else if (a.dayOfWeek.id === b.dayOfWeek.id) {
                return a.pairTime.id > b.pairTime.id ? 1 : -1
            }
        }
        data.sort(compare);

        console.log(data);

        var table = document.createElement('table');
        table.innerHTML = '<tbody>';
        b = ['понедельник', 'вторник', 'среда', 'четверг', 'пятница', 'суббота'];
        c = ['8:30', '10:10', "11:50", "13:20", "14:00", "15:40", "17:50", "19:30"];
        for (i = 0; i < 7 * 7 - 1; i++) {
            table.innerHTML += '<tr>';

            currentDayOfWeek = b[i / 8];
            currentTime = c[i % 8];

            if (i % 8 === 0) {
                table.innerHTML += '<td>' + b[i / 8] + " " + c[i % 8] + '</td>';
            } else {
                table.innerHTML += '<td>' + c[i % 8] + '</td>';
            }
            for (j = 0; j < 6; j++)

                for (k = 0; k < data.length; k ++) {
                    console.log(data[k].dayOfWeek + " " + data[k].pairTime);
                    console.log(currentDayOfWeek + " " + currentTime);
                    ok = false;
                    if (data[k].dayOfWeek == currentDayOfWeek && data[k].pairTime == currentTime) {
                        ok = true;
                    }
                }

            if (ok) {
                table.innerHTML += '<td>' + data[k].subject.name + ' ' + data[k].teacher.name + '</td>';

            } else {
                table.innerHTML += '<td></td>';
            }

            table.innerHTML += '</tr>';
            table.innerHTML += '<br>';
        }
        table.innerHTML += '</tbody>';

        document.body.append(table);
    };

    function httpGet() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "http://localhost:8080/uni_timetable/timetable", false);
        xmlHttp.send(null);
        return xmlHttp.responseText;
    }
};

