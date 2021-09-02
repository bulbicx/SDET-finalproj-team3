/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 43.33243741360774, "KoPercent": 56.66756258639226};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.026560820150514513, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "Read Genre"], "isController": false}, {"data": [0.0, 500, 1500, "Update Playlist"], "isController": false}, {"data": [0.0, 500, 1500, "Read Playlist One"], "isController": false}, {"data": [0.0, 500, 1500, "Delete Playlist"], "isController": false}, {"data": [0.0, 500, 1500, "Read Genre One"], "isController": false}, {"data": [0.0, 500, 1500, "Read Playlist"], "isController": false}, {"data": [0.0, 500, 1500, "Create Playlist"], "isController": false}, {"data": [0.0, 500, 1500, "Read User One"], "isController": false}, {"data": [0.0, 500, 1500, "Create Genre"], "isController": false}, {"data": [4.7966231772831924E-4, 500, 1500, "Update Artist"], "isController": false}, {"data": [0.0, 500, 1500, "Update Genre"], "isController": false}, {"data": [0.20240073868882733, 500, 1500, "Create Artist"], "isController": false}, {"data": [0.0, 500, 1500, "Create User"], "isController": false}, {"data": [0.0, 500, 1500, "Update User"], "isController": false}, {"data": [0.0, 500, 1500, "Delete Genre"], "isController": false}, {"data": [0.008511443162473993, 500, 1500, "Read Artist One"], "isController": false}, {"data": [0.0, 500, 1500, "Read User"], "isController": false}, {"data": [0.0447078588762367, 500, 1500, "Read Artist"], "isController": false}, {"data": [1.0377750103777501E-4, 500, 1500, "Delete Artist"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 52088, 29517, 56.66756258639226, 48014.341882967194, 1, 411634, 19709.5, 222946.1, 385232.95, 399829.71, 78.07352293132445, 3296.843561633274, 7.963804641573461], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Read Genre", 3647, 2484, 68.11077598025774, 17971.333424732635, 2016, 315086, 2049.0, 27187.2, 103853.0, 245938.08, 6.2140269689111225, 93.12007926632907, 0.3580048060949262], "isController": false}, {"data": ["Update Playlist", 616, 612, 99.35064935064935, 15689.790584415578, 2022, 219642, 2050.0, 25449.7, 86539.79999999996, 166075.98000000024, 1.912265506890054, 3.5994854868826662, 0.1987133146142408], "isController": false}, {"data": ["Read Playlist One", 739, 473, 64.00541271989175, 16663.610284167793, 2024, 242692, 2049.0, 27019.0, 98413.0, 196865.8, 2.223512668606348, 991.3262008877125, 0.14813701832667883], "isController": false}, {"data": ["Delete Playlist", 490, 490, 100.0, 14696.357142857138, 2023, 182959, 2050.0, 25592.6, 83685.94999999991, 152044.96, 1.6323212941309722, 2.872239393867469, 0.12264856726329676], "isController": false}, {"data": ["Read Genre One", 3149, 2167, 68.81549698316925, 17204.19688790092, 2021, 314057, 2049.0, 26881.0, 88757.5, 244562.5, 7.112157068228376, 57.160134379171254, 0.4046412104218284], "isController": false}, {"data": ["Read Playlist", 879, 546, 62.1160409556314, 20304.92036405004, 2025, 251894, 2050.0, 42808.0, 117239.0, 210681.00000000012, 2.5062013514669403, 2520.776757695556, 0.17431277264562484], "isController": false}, {"data": ["Create Playlist", 1004, 667, 66.43426294820718, 17713.5816733068, 2024, 249235, 2049.0, 27003.5, 130177.5, 210087.55000000016, 2.800909458648924, 15.248293784261623, 0.2726790365945516], "isController": false}, {"data": ["Read User One", 1457, 940, 64.51612903225806, 17729.560054907335, 2023, 256623, 2049.0, 27058.0, 95885.59999999985, 225236.94000000003, 3.6279157785701477, 26.99910519866387, 0.23354114506558635], "isController": false}, {"data": ["Create Genre", 4167, 2845, 68.27453803695704, 17456.245500359975, 2020, 399008, 2049.0, 26919.0, 88344.2, 249209.03999999998, 6.500161451094358, 11.897078327471956, 0.5316625484938251], "isController": false}, {"data": ["Update Artist", 5212, 3388, 65.00383729854182, 52099.49539524178, 10, 403858, 2049.0, 238852.8999999999, 360011.8999999998, 399003.14, 7.930325125071512, 13.61341414799216, 0.6755925300962837], "isController": false}, {"data": ["Update Genre", 2699, 2684, 99.44423860689145, 19167.49425713229, 2022, 308503, 2049.0, 25344.0, 125635.0, 264792.0, 6.285617138611948, 11.640021040602665, 0.5146322650520619], "isController": false}, {"data": ["Create Artist", 5415, 246, 4.542936288088643, 34527.03915050795, 1, 195461, 22297.0, 91759.8, 106296.0, 127237.72000000002, 8.129593069953534, 3.27957774017205, 1.7743723586329072], "isController": false}, {"data": ["Create User", 1975, 1295, 65.56962025316456, 19183.531645569597, 2022, 294372, 2049.0, 27005.4, 129663.5999999999, 242107.72, 4.76059624358826, 8.550368878415577, 0.45459174572872074], "isController": false}, {"data": ["Update User", 1180, 1179, 99.91525423728814, 17868.566949152544, 2022, 246074, 2049.0, 25500.6, 110664.65, 220332.61000000004, 3.1479342348519004, 5.690421551411369, 0.29521783271397284], "isController": false}, {"data": ["Delete Genre", 2266, 2262, 99.82347749338041, 19572.97087378642, 2021, 300753, 2049.0, 25557.2, 134010.70000000004, 239125.60999999996, 5.386286599888757, 9.788884336269724, 0.36853745144973354], "isController": false}, {"data": ["Read Artist One", 5287, 2656, 50.23642897673539, 96535.6073387554, 1, 409400, 2053.0, 361437.9999999999, 393379.5999999999, 399702.84, 8.002203746672055, 31.948095125118627, 0.7423490845975362], "isController": false}, {"data": ["Read User", 1731, 1093, 63.14269208549971, 21410.82726747543, 2023, 286993, 2050.0, 27569.4, 154156.39999999956, 234583.44, 4.278542774305015, 58.44682764343994, 0.2833592245728254], "isController": false}, {"data": ["Read Artist", 5357, 213, 3.9761060294941197, 176291.8150084001, 12, 411634, 166226.0, 378457.8, 394857.5, 401111.94, 8.045790566134084, 1202.90884365322, 1.4055164054826124], "isController": false}, {"data": ["Delete Artist", 4818, 3277, 68.01577418015775, 23342.25633042758, 1346, 400650, 2049.0, 27147.300000000003, 154011.75000000006, 367559.3600000002, 7.39450400190311, 12.956738355440361, 0.5191331999685372], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 26945, 91.28637734187079, 51.72976501305483], "isController": false}, {"data": ["500", 20, 0.06775756343801877, 0.03839655966825373], "isController": false}, {"data": ["404", 2552, 8.645865094691194, 4.899401013669175], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 52088, 29517, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 26945, "404", 2552, "500", 20, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Read Genre", 3647, 2484, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 2484, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Update Playlist", 616, 612, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 390, "404", 222, null, null, null, null, null, null], "isController": false}, {"data": ["Read Playlist One", 739, 473, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 473, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Delete Playlist", 490, 490, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 313, "404", 177, null, null, null, null, null, null], "isController": false}, {"data": ["Read Genre One", 3149, 2167, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 2167, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Read Playlist", 879, 546, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 546, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Create Playlist", 1004, 667, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 667, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Read User One", 1457, 940, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 940, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Create Genre", 4167, 2845, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 2845, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Update Artist", 5212, 3388, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 3297, "404", 89, "500", 2, null, null, null, null], "isController": false}, {"data": ["Update Genre", 2699, 2684, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 1842, "404", 842, null, null, null, null, null, null], "isController": false}, {"data": ["Create Artist", 5415, 246, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 243, "500", 3, null, null, null, null, null, null], "isController": false}, {"data": ["Create User", 1975, 1295, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 1295, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Update User", 1180, 1179, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 781, "404", 398, null, null, null, null, null, null], "isController": false}, {"data": ["Delete Genre", 2266, 2262, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 1510, "404", 752, null, null, null, null, null, null], "isController": false}, {"data": ["Read Artist One", 5287, 2656, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 2650, "500", 6, null, null, null, null, null, null], "isController": false}, {"data": ["Read User", 1731, 1093, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 1093, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Read Artist", 5357, 213, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 205, "500", 8, null, null, null, null, null, null], "isController": false}, {"data": ["Delete Artist", 4818, 3277, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to 127.0.0.1:8082 [/127.0.0.1] failed: Connection refused: connect", 3204, "404", 72, "500", 1, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
